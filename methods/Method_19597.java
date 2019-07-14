private static Drawable buildCircleDrawable(ComponentContext c,int color,int radiusDp){
  final float radiusPx=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,radiusDp,c.getResources().getDisplayMetrics());
  final float[] radii=new float[8];
  Arrays.fill(radii,radiusPx);
  final RoundRectShape roundedRectShape=new RoundRectShape(radii,null,radii);
  final ShapeDrawable drawable=new ShapeDrawable(roundedRectShape);
  drawable.getPaint().setColor(color);
  return drawable;
}
