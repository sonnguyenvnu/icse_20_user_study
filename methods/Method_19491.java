private static Drawable buildRoundedRect(ComponentContext c,int color,int cornerRadiusDp){
  final float cornerRadiusPx=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,cornerRadiusDp,c.getResources().getDisplayMetrics());
  final float[] radii=new float[8];
  Arrays.fill(radii,cornerRadiusPx);
  final RoundRectShape roundedRectShape=new RoundRectShape(radii,null,radii);
  final ShapeDrawable drawable=new ShapeDrawable(roundedRectShape);
  drawable.getPaint().setColor(color);
  return drawable;
}
