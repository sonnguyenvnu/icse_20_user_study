private void setAnimationProgressInernal(TextView newTab,TextView prevTab,float value){
  int newColor=Theme.getColor(Theme.key_actionBarTabActiveText);
  int prevColor=Theme.getColor(Theme.key_actionBarTabUnactiveText);
  int r1=Color.red(newColor);
  int g1=Color.green(newColor);
  int b1=Color.blue(newColor);
  int a1=Color.alpha(newColor);
  int r2=Color.red(prevColor);
  int g2=Color.green(prevColor);
  int b2=Color.blue(prevColor);
  int a2=Color.alpha(prevColor);
  prevTab.setTextColor(Color.argb((int)(a1 + (a2 - a1) * value),(int)(r1 + (r2 - r1) * value),(int)(g1 + (g2 - g1) * value),(int)(b1 + (b2 - b1) * value)));
  newTab.setTextColor(Color.argb((int)(a2 + (a1 - a2) * value),(int)(r2 + (r1 - r2) * value),(int)(g2 + (g1 - g2) * value),(int)(b2 + (b1 - b2) * value)));
  indicatorX=(int)(animateIndicatorStartX + (animateIndicatorToX - animateIndicatorStartX) * value);
  indicatorWidth=(int)(animateIndicatorStartWidth + (animateIndicatorToWidth - animateIndicatorStartWidth) * value);
  invalidate();
}
