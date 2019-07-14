private void drawVerticalScrollbar(Canvas canvas){
  int viewWidth=mRecyclerViewWidth;
  int left=viewWidth - mVerticalThumbWidth;
  int top=mVerticalThumbCenterY - mVerticalThumbHeight / 2;
  mVerticalThumbDrawable.setBounds(0,0,mVerticalThumbWidth,mVerticalThumbHeight);
  mVerticalTrackDrawable.setBounds(0,0,mVerticalTrackWidth,mRecyclerViewHeight);
  if (isLayoutRTL()) {
    mVerticalTrackDrawable.draw(canvas);
    canvas.translate(mVerticalThumbWidth,top);
    canvas.scale(-1,1);
    mVerticalThumbDrawable.draw(canvas);
    canvas.scale(1,1);
    canvas.translate(-mVerticalThumbWidth,-top);
  }
 else {
    canvas.translate(left,0);
    mVerticalTrackDrawable.draw(canvas);
    canvas.translate(0,top);
    mVerticalThumbDrawable.draw(canvas);
    canvas.translate(-left,-top);
  }
}
