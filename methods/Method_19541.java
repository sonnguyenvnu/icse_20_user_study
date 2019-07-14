private static Drawable buildRoundedRect(int radius,int color){
  final float[] radii=new float[8];
  Arrays.fill(radii,radius);
  final RoundRectShape roundedRectShape=new RoundRectShape(radii,null,radii);
  final ShapeDrawable drawable=new ShapeDrawable(roundedRectShape);
  drawable.getPaint().setColor(color);
  return drawable;
}
