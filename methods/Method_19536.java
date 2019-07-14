static ShapeDrawable getMessageBackground(ComponentContext c,int color){
  final RoundRectShape roundedRectShape=new RoundRectShape(new float[]{40,40,40,40,40,40,40,40},null,new float[]{40,40,40,40,40,40,40,40});
  final ShapeDrawable oval=new ShapeDrawable(roundedRectShape);
  oval.getPaint().setColor(color);
  return oval;
}
