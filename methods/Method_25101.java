private Drawable createRectDrawable(int color){
  RoundRectShape shape=new RoundRectShape(new float[]{mCornerRadius,mCornerRadius,mCornerRadius,mCornerRadius,mCornerRadius,mCornerRadius,mCornerRadius,mCornerRadius},null,null);
  ShapeDrawable shapeDrawable=new ShapeDrawable(shape);
  shapeDrawable.getPaint().setColor(color);
  return shapeDrawable;
}
