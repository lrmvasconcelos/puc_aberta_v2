package pucaberta.pucminas.com.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import pucaberta.pucminas.com.R;
import rx.functions.Action1;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class Utils {
    public static Object jsonToObject(String obj, Class<?> classModel) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
//                .serializeNulls()
                .create();
        return gson.fromJson(obj, classModel);
    }

    public static Object jsonToObject(String obj, Type classModel) {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
//                .serializeNulls()
                .create();
        return gson.fromJson(obj, classModel);
    }

    public static String objectToString(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                    .serializeNulls()
                    .create();
            return gson.toJson(obj);
        } catch (Exception ex) {
            return "";
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null || TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static void showSimpleDialog(Context context, int title, int resString, MaterialDialog.SingleButtonCallback callback) {
        showSimpleDialog(context, context.getString(title), context.getString(resString), callback);
    }

    public static void showSimpleDialog(Context context, String title, String text, MaterialDialog.SingleButtonCallback callback) {
        try {
            AnimationsUtil.shakeError(new MaterialDialog.Builder(context)
                    .title(title)
                    .content(text)
                    .cancelable(false)
                    .positiveText(R.string.ok)
                    .onPositive(callback)
                    .show().getView(), AnimationsUtil.DURATION_DIALOG);

        } catch (Exception ex) {
            Log.e("asd", "ads");
        }
    }

    public static void showDialogError(Context context, Throwable t, MaterialDialog.SingleButtonCallback callback) {
        new ExceptionUtils(context, t, callback).show();
    }

    public static String getStringToInputStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }

//    public static void deleteCache(Context context) {
//        File pathDad = context.getCacheDir();
//        if (pathDad.isDirectory()) {
//            for (String file : pathDad.list()) {
//                File itemCurrent = new File(pathDad.getPath(), file);
//                if (itemCurrent.isFile()) {
//                    try {
//                        deleteFile(pathDad.getPath() + "/" + file);
//                    } catch (IOException e) {
//                    }
//                } else {
//                    for (String file2 : itemCurrent.list()) {
//                        try {
//                            deleteFile(itemCurrent.getPath() + "/" + file2);
//                        } catch (IOException e) {
//                        }
//                    }
//                }
//            }
//        }
//    }

//    public static void deleteFile(String path) throws IOException {
//        File file = new File(path);
//        file.delete();
//        if (file.exists()) {
//            file.getCanonicalFile().delete();
//            if (file.exists()) {
//                SaraivaApp.getInstance().getApplicationContext().deleteFile(file.getName());
//            }
//        }
//    }

    public static void writeResponseBodyToDisk(File futureStudioIconFile, ResponseBody body, Action1<Long> actionTotal, Action1<Long> actionProgress) {
        try {

            futureStudioIconFile.setReadable(true, false);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                actionTotal.call(fileSize);

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    actionProgress.call(fileSizeDownloaded);
                }

                outputStream.flush();
            } catch (IOException e) {
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
        }
    }

//    public static HashMap<String, String> getRequestContextoUsuario() {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("token", SaraivaApp.getInstance().getUserModel().getToken());
//        return hashMap;
//    }


    public static void hideKeyboard(Context ctx) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }


//    public static boolean verifyDataUpdate(String dateString) {
//        if (!TextUtils.isEmpty(dateString)) {
//            SimpleDateFormat simpleDateFormatString = new SimpleDateFormat("yyyyddMMHHmmss");
//            try {
//                Date date = simpleDateFormatString.parse(dateString);
//                Date dateLast = simpleDateFormatString.parse(PrefsHelper.getLastDownload());
//                if (dateLast.before(date)) {
//                    return true;
//                } else
//                    return false;
//            } catch (ParseException e) {
//                return true;
//            }
//        } else {
//            return true;
//        }
//    }

    public static String getDataUltimoDownload() {
        SimpleDateFormat simpleDateFormatString = new SimpleDateFormat("yyyyddMMHHmmss");
        return simpleDateFormatString.format(Calendar.getInstance().getTime());
    }

    private static void verifyCreatePath() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Saraiva/");
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private static String getPathAppFile() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Saraiva/";
    }

    public static File getFileToSave(String path) {
        verifyCreatePath();
        return new File(getPathAppFile(), getNameFile(path));
    }

    public static String getNameFile(String path) {
        String[] newPath = path.split("/");
        return newPath[newPath.length - 1];
    }

    public static boolean isDownloadFile(Context context, String path) {
        return !TextUtils.isEmpty(getDownloadFile(context, path));
    }

    public static String getDownloadFile(Context context, String path) {
        String nomeArquivo = getNameFile(path);
        File directory = new File(getPathAppFile());
        if (directory.isDirectory() && directory.list() != null) {
            for (String file : directory.list()) {
                if (file.equalsIgnoreCase(nomeArquivo)) {
                    return directory.getAbsolutePath() + "/" + file;
                }
            }
        }

        return null;
    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "capa", null);
            return Uri.parse(path);
        } catch (Exception ex) {
            return null;
        }
    }

//    public static void shareMsg(Context context, String msg) {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
//        sendIntent.setType("text/plain");
//        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        context.startActivity(Intent.createChooser(sendIntent,
//                context.getString(R.string.action_share))
//                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//    }

//    public static void shareMsgImage(Context context, String msg, Bitmap bitmap) {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
//        Uri uri = getImageUri(context, bitmap);
//        if (uri != null) {
//            sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
//        }
//        sendIntent.setType("*/*");
//        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        context.startActivity(Intent.createChooser(sendIntent,
//                context.getString(R.string.action_share))
//                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//    }

//    public static void openPdf(Context context, String pathFile) {
//        try {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            Uri uri = FileProvider.getUriForFile(context, "br.com.saraivaconecta.androidapp.provider", new File(pathFile));
//            intent.setDataAndType(uri, "application/pdf");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException ex) {
//            showSimpleDialog(context, R.string.erro_app_nao_encontrado, (dialog, which) -> {
//            });
//        }
//    }

//    public static String getPathImageFileFir(String codProduto) {
//        return SaraivaApp.getInstance().getFilesDir().getAbsolutePath() + "/" + codProduto;
//    }
//
//    public static Observable<File> saveFileDownload(Response<ResponseBody> response, File filePath) {
//        try {
//            // you can access headers of response
////            String header = response.headers().get("Content-Disposition");
////            // this is specific case, it's up to you how you want to save your file
////            // if you are not downloading file from direct link, you might be lucky to obtain file name from header
////            String fileName = header.replace("attachment; filename=", "");
//            // will create file in global Music directory, can be any other directory, just don't forget to handle permissions
////            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsoluteFile(), fileName);
////            File file = new File(filePath);
//
//            BufferedSink sink = Okio.buffer(Okio.sink(filePath));
//            // you can access body of response
//            sink.writeAll(response.body().source());
//            sink.close();
//            return Observable.just(filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Observable.error(e);
//        }
//    }
}
