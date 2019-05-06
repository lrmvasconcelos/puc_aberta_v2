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
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivitySharePicBinding;
import pucaberta.pucminas.com.model.ImageModel;
import pucaberta.pucminas.com.ui.SlidingImage_Adapter;
import pucaberta.pucminas.com.viewmodel.HomeViewModel;

public class SharePicActivity extends BaseActivityViewModel<ActivitySharePicBinding, HomeViewModel> {

    private static final String BYTE_ARRAY = "BYTE_ARRAY";

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    private Bitmap currrentBitmap;

    private int[] myImageList = new int[]{R.drawable.slide01_app_pucaberta, R.drawable.slide02_app_pucaberta,
            R.drawable.slide03_app_pucaberta, R.drawable.slide04_app_pucaberta};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_share_pic);
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            byte[] byteArray = extras.getByteArray(BYTE_ARRAY);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            currrentBitmap = bmp;
            mBinding.image.setImageBitmap(bmp);
        }

        mBinding.share.setOnClickListener(view -> {
            checkWritePermissionAndShare(mBinding.content, "puc_aberta.jpeg");
        });

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init();
    }

    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < myImageList.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(SharePicActivity.this, imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();


        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
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

    private String screenShot(View view, String fileName) {


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

    private Bitmap loadLargeBitmapFromView(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(canvas);
        return bitmap;
    }
}
