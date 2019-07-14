private void createFillTransition(){
  select=new JFXFillTransition(Duration.millis(120),box,Color.TRANSPARENT,(Color)getSkinnable().getCheckedColor());
  select.setInterpolator(Interpolator.EASE_OUT);
}
