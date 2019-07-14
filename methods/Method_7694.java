public static Drawable createCircleDrawable(int size,int color){
  OvalShape ovalShape=new OvalShape();
  ovalShape.resize(size,size);
  ShapeDrawable defaultDrawable=new ShapeDrawable(ovalShape);
  defaultDrawable.getPaint().setColor(color);
  return defaultDrawable;
}
