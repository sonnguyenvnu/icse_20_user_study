@Override public boolean dispatchTouchEvent(MotionEvent event){
  final float y=event.getY();
  final float x=event.getX();
  final int oldChoose=mChoose;
  final int newChoose=(int)(y / mHeight * mLetters.size());
switch (event.getAction()) {
case MotionEvent.ACTION_DOWN:
    if (x < mWidth - 2 * mRadius) {
      return false;
    }
  startAnimator(mRatio,1.0f);
break;
case MotionEvent.ACTION_MOVE:
mCenterY=(int)y;
if (oldChoose != newChoose) {
if (newChoose >= 0 && newChoose < mLetters.size()) {
mChoose=newChoose;
if (listener != null) {
  listener.onLetterChange(mLetters.get(newChoose));
}
}
}
invalidate();
break;
case MotionEvent.ACTION_CANCEL:
case MotionEvent.ACTION_UP:
startAnimator(mRatio,0f);
mChoose=-1;
break;
default :
break;
}
return true;
}
