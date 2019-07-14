private Drawable createOuterStrokeDrawable(float strokeWidth){
  ShapeDrawable shapeDrawable=new ShapeDrawable(new OvalShape());
  final Paint paint=shapeDrawable.getPaint();
  paint.setAntiAlias(true);
  paint.setStrokeWidth(strokeWidth);
  paint.setStyle(Style.STROKE);
  paint.setColor(Color.BLACK);
  paint.setAlpha(opacityToAlpha(0.02f));
  return shapeDrawable;
}
