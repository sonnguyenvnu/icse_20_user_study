public static Drawable createRoundRectDrawable(int rad,int defaultColor){
  ShapeDrawable defaultDrawable=new ShapeDrawable(new RoundRectShape(new float[]{rad,rad,rad,rad,rad,rad,rad,rad},null,null));
  defaultDrawable.getPaint().setColor(defaultColor);
  return defaultDrawable;
}
