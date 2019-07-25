public static void tanslation(final View view,float start,float end){
  final ValueAnimator animator=ValueAnimator.ofFloat(start,end);
  view.setVisibility(View.VISIBLE);
  animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator valueAnimator){
      Float value=(Float)animator.getAnimatedValue();
      view.setTranslationY(value);
    }
  }
);
  animator.setDuration(200);
  animator.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
  animator.start();
}
