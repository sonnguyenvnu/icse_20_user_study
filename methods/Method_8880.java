public void onTouch(MotionEvent event){
  if (event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
    LayoutParams layoutParams=(LayoutParams)textureView.getLayoutParams();
    if (layoutParams != null && event.getX() >= layoutParams.leftMargin && event.getY() >= layoutParams.topMargin && event.getX() <= layoutParams.leftMargin + layoutParams.width && event.getY() <= layoutParams.topMargin + layoutParams.height) {
      setShowOriginal(true);
    }
  }
 else   if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
    setShowOriginal(false);
  }
}
