private Drawable createCircleDrawable(int color){
  CircleDrawable shapeDrawable=new CircleDrawable(new OvalShape());
  shapeDrawable.getPaint().setColor(color);
  return shapeDrawable;
}
