package pucaberta.pucminas.com.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivitySharePicBinding;
import pucaberta.pucminas.com.viewmodel.HomeViewModel;

public class SharePicActivity extends BaseActivityViewModel<ActivitySharePicBinding, HomeViewModel> {

    private static final String BYTE_ARRAY = "BYTE_ARRAY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_share_pic);
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            byte[] byteArray = extras.getByteArray(BYTE_ARRAY);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            mBinding.image.setImageBitmap(bmp);
        }

        mBinding.share.setOnClickListener(view -> {
            checkWritePermissionAndShare(mBinding.content, "puc_aberta.jpeg");
        });
    }

    public void checkWritePermissionAndShare(View view,
                                             String fileName) {
        if (view.getContext() instanceof Activity) {
            new RxPermissions((Activity) view.getContext())
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .filter(allGranted -> allGranted)
                    .subscribe(allGranted -> shareReceipt(view, fileName), error -> {
                    });
        }
    }

    private void shareReceipt(View view, String fileName) {
        String url = screenShot(view, fileName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Context context = view.getContext();
            Uri contentUri = FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".fileprovider",
                    new File(url));

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(Intent.EXTRA_STREAM, contentUri);
            intent.setDataAndType(contentUri, "image/jpg");

            PackageManager pm = context.getPackageManager();
            if (intent.resolveActivity(pm) != null) {
                context.startActivity(Intent.createChooser(intent, "Compartilhar"));
            }
        } else {
            Uri bitmapUri = Uri.fromFile(new File(url));
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);

            Context context = view.getContext();

            context.startActivity(Intent.createChooser(intent, "Compartilhar"));
        }
    }

    private static String screenShot(View view, String fileName) {

        Context context = view.getContext();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(Color.WHITE);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        ArrayList<View> children = new ArrayList<>();

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                children.add(parent.getChildAt(i));
            }
            parent.removeAllViews();
        }

        linearLayout.addView(view);

        linearLayout.setDrawingCacheEnabled(true);
        linearLayout.measure(View.MeasureSpec.makeMeasureSpec(
                0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(
                        0, View.MeasureSpec.UNSPECIFIED));
        linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(),
                linearLayout.getMeasuredHeight());
        linearLayout.buildDrawingCache(true);
        Bitmap bitmap;
        if (linearLayout.getDrawingCache() == null) {
            bitmap = loadLargeBitmapFromView(linearLayout);
        } else {
            bitmap = Bitmap.createBitmap(
                    linearLayout.getDrawingCache());
        }
        linearLayout.setDrawingCacheEnabled(false);

        linearLayout.removeView(view);
        if (parent != null) {
            for (View v : children) {
                parent.addView(v);
            }
        }

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(path, fileName == null ? "puc_aberta.jpeg" : fileName);
        FileOutputStream fileOutPutStream = null;
        try {
            fileOutPutStream = new FileOutputStream(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutPutStream);

        try {
            if (fileOutPutStream != null) {
                fileOutPutStream.flush();
                fileOutPutStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File fileReturn = new File("file://" + imageFile.getAbsolutePath());
        if (fileReturn.exists()) {
            return fileReturn.getAbsolutePath();
        } else {
            fileReturn = new File(imageFile.getAbsolutePath());
            return fileReturn.getAbsolutePath();
        }
    }

    private static Bitmap loadLargeBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }
}
