@NonNull private static Drawable getRippleMask(int color){
  float[] outerRadii=new float[8];
  Arrays.fill(outerRadii,3);
  RoundRectShape r=new RoundRectShape(outerRadii,null,null);
  ShapeDrawable shapeDrawable=new ShapeDrawable(r);
  shapeDrawable.getPaint().setColor(color);
  return shapeDrawable;
}
