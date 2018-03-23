package pucaberta.pucminas.com.helper;

import android.animation.Animator;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class AnimationsUtil {
    public static long DURATION_300 = 300;
    public static long DURATION_400 = 400;
    public static long DURATION_DIALOG = 500;

    public static void shakeError(View view, long duartion) {
        if (view != null) {
            YoYo.with(Techniques.Shake).duration(duartion).playOn(view);
        }
    }

    public static void slideOutUp(final View view) {
        if (view != null && view.getVisibility() == View.VISIBLE) {
            YoYo.with(Techniques.SlideOutUp)
                    .duration(400)
                    .withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            view.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .playOn(view);

        }
    }

    public static void slideInDown(final View view) {
        if (view != null && view.getVisibility() == View.GONE) {
            YoYo.with(Techniques.SlideInDown)
                    .duration(400)
                    .withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            view.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .playOn(view);
        }
    }

    public static void fadeIn(final View view, long duration) {
        YoYo.with(Techniques.FadeIn)
                .duration(duration)
                .playOn(view);
    }

    public static void fadOut(final View view, long duration) {
        YoYo.with(Techniques.FadeOut)
                .duration(duration)
                .playOn(view);
    }

    public static void slideInRight(final View view, long duration) {
        YoYo.with(Techniques.SlideInRight)
                .duration(duration)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(view);
    }

    public static void slideInLeft(final View view, long duration) {
        YoYo.with(Techniques.SlideInLeft)
                .duration(duration)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .playOn(view);
    }
}
