@Override public Circle build(){
  return SPATIAL_CONTEXT.makeCircle(center.x,center.y,360 * radius / unit.getEarthCircumference());
}
