void updateIndicator(int index){
  if (index != 0 && index != 1)   return;
  int accentColor=mainActivity.getAccent();
  if (index == 0) {
    circleDrawable1.setImageDrawable(new ColorCircleDrawable(accentColor));
    circleDrawable2.setImageDrawable(new ColorCircleDrawable(Color.GRAY));
  }
 else {
    circleDrawable1.setImageDrawable(new ColorCircleDrawable(accentColor));
    circleDrawable2.setImageDrawable(new ColorCircleDrawable(Color.GRAY));
  }
}
