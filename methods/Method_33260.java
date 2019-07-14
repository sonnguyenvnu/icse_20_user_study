private void refreshHSLCircle(){
  ColorAdjust colorAdjust=new ColorAdjust();
  colorAdjust.setHue(map(currentHue + (currentHue < 127.5 ? 1 : -1) * 127.5,0,255,-1,1));
  slCircleView.setEffect(colorAdjust);
  setColorAtLocation((int)selector.getTranslateX() + selectorSize / 2,(int)selector.getTranslateY() + selectorSize / 2);
}
