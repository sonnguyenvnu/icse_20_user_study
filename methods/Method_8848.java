@Override protected Rect getSelectionBounds(){
  ViewGroup parentView=(ViewGroup)getParent();
  float scale=parentView.getScaleX();
  float side=getWidth() * (getScale() + 0.4f);
  return new Rect((position.x - side / 2.0f) * scale,(position.y - side / 2.0f) * scale,side * scale,side * scale);
}
