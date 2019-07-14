private void regenerateBackground(){
  if (selectionDrawable != null) {
    setBackgroundDrawable(selectionDrawable);
  }
 else {
    mCircleDrawable=generateBackground(selectionColor,fadeTime,circleDrawableRect);
    setBackgroundDrawable(mCircleDrawable);
  }
}
