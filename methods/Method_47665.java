@Override protected void onSizeChanged(int width,int height,int oldWidth,int oldHeight){
  this.width=width;
  Context context=getContext();
  float minTextSize=getDimension(context,R.dimen.tinyTextSize);
  float maxTextSize=getDimension(context,R.dimen.regularTextSize);
  float textSize=baseSize * 0.5f;
  paint.setTextSize(Math.max(Math.min(textSize,maxTextSize),minTextSize));
  em=paint.getFontSpacing();
  textMargin=0.5f * em;
  updateMaxMinLengths();
}
