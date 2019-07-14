private static Drawable generateCircleDrawable(final int color){
  ShapeDrawable drawable=new ShapeDrawable(new OvalShape());
  drawable.getPaint().setColor(color);
  return drawable;
}
