@Override protected Rect getSelectionBounds(){
  ViewGroup parentView=(ViewGroup)getParent();
  float scale=parentView.getScaleX();
  float width=getWidth() * (getScale()) + AndroidUtilities.dp(46) / scale;
  float height=getHeight() * (getScale()) + AndroidUtilities.dp(20) / scale;
  return new Rect((position.x - width / 2.0f) * scale,(position.y - height / 2.0f) * scale,width * scale,height * scale);
}
