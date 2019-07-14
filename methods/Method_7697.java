public static Drawable createRoundRectDrawableWithIcon(int rad,int iconRes){
  ShapeDrawable defaultDrawable=new ShapeDrawable(new RoundRectShape(new float[]{rad,rad,rad,rad,rad,rad,rad,rad},null,null));
  defaultDrawable.getPaint().setColor(0xffffffff);
  Drawable drawable=ApplicationLoader.applicationContext.getResources().getDrawable(iconRes).mutate();
  return new CombinedDrawable(defaultDrawable,drawable);
}
