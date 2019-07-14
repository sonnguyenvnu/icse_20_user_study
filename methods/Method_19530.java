static ShapeDrawable getCircle(ComponentContext c){
  final ShapeDrawable oval=new ShapeDrawable(new OvalShape());
  oval.getPaint().setColor(Color.LTGRAY);
  return oval;
}
