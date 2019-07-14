public void reset(View view,long duration){
  if (view == null) {
    return;
  }
  view.clearAnimation();
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
    android.animation.ObjectAnimator.ofFloat(view,"translationY",0F).setDuration(duration).start();
  }
 else {
    com.nineoldandroids.animation.ObjectAnimator.ofFloat(view,"translationY",0F).setDuration(duration).start();
  }
}
