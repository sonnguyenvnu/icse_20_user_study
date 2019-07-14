public void setImageSpan(DraweeHolder draweeHolder,int startIndex,int endIndex,final int drawableWidthPx,final int drawableHeightPx,boolean enableResizing,@BetterImageSpan.BetterImageSpanAlignment int verticalAlignment){
  if (endIndex >= length()) {
    return;
  }
  Drawable topLevelDrawable=draweeHolder.getTopLevelDrawable();
  if (topLevelDrawable != null) {
    if (topLevelDrawable.getBounds().isEmpty()) {
      topLevelDrawable.setBounds(0,0,drawableWidthPx,drawableHeightPx);
    }
    topLevelDrawable.setCallback(mDrawableCallback);
  }
  DraweeSpan draweeSpan=new DraweeSpan(draweeHolder,verticalAlignment);
  final DraweeController controller=draweeHolder.getController();
  if (controller instanceof AbstractDraweeController) {
    ((AbstractDraweeController)controller).addControllerListener(new DrawableChangedListener(draweeSpan,enableResizing,drawableHeightPx));
  }
  mDraweeSpans.add(draweeSpan);
  setSpan(draweeSpan,startIndex,endIndex + 1,SPAN_EXCLUSIVE_EXCLUSIVE);
}
