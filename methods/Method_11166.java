@Override public void onAnimationUpdate(ValueAnimator animation){
  final Callback callback=getCallback();
  if (callback != null) {
    callback.invalidateDrawable(this);
  }
}
