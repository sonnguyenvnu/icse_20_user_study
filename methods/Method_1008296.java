@Override public Rectangle build(){
  return SPATIAL_CONTEXT.makeRectangle(topLeft.x,bottomRight.x,bottomRight.y,topLeft.y);
}
