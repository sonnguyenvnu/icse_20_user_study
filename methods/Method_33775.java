public void smoothTo(View view,float y,long duration){
  if (view == null) {
    return;
  }
  view.clearAnimation();
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
    android.animation.ObjectAnimator.ofFloat(view,"translationY",y).setDuration(duration).start();
  }
 else {
    com.nineoldandroids.animation.ObjectAnimator.ofFloat(view,"translationY",y).setDuration(duration).start();
  }
}
