package pucaberta.pucminas.com.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.camerakit.CameraKitView;

import java.io.IOException;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityCameraBinding;
import pucaberta.pucminas.com.viewmodel.HomeViewModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CameraActivity extends BaseActivityViewModel<ActivityCameraBinding, HomeViewModel> {

    private static final String BYTE_ARRAY = "BYTE_ARRAY";
    private CameraKitView cameraKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera);
        cameraKitView = mBinding.camera;

        mBinding.btnTakePhoto.setOnClickListener(view ->
                cameraKitView.captureImage((cameraKitView, jpeg) -> {
                    Intent i = new Intent(this, SharePicActivity.class);
                    i.putExtra(BYTE_ARRAY, jpeg);
                    startActivity(i);
                }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private Observable<Bitmap> processBitmap(byte[] pic) {
        Bitmap scaledBitmap;

        try {
            scaledBitmap = handleSamplingAndRotationBitmap(pic);
        } catch (IOException e) {
            // god forbids
            scaledBitmap = null;
        }

        return Observable.just(scaledBitmap);
    }

    public static Bitmap handleSamplingAndRotationBitmap(byte[] selectedImage)
            throws IOException {
        int MAX_HEIGHT = 1024;
        int MAX_WIDTH = 1024;
        return handleSamplingAndRotationBitmap(selectedImage, MAX_WIDTH, MAX_HEIGHT);
    }

    public static Bitmap handleSamplingAndRotationBitmap(byte[] selectedImage,
                                                         int maxWidth, int maxHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(selectedImage, 0, selectedImage.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        Bitmap img = BitmapFactory.decodeByteArray(selectedImage, 0, selectedImage.length, options);
//        img = rotateImageIfRequired(img, selectedImage);
//        return BitmapFactory.decodeStream(imageStream, null, profileOptions);
        return img;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }
}
