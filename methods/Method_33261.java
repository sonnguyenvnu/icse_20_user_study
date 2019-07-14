public void moveToColor(Color color){
  allowColorChange=false;
  double max=Math.max(color.getRed(),Math.max(color.getGreen(),color.getBlue())), min=Math.min(color.getRed(),Math.min(color.getGreen(),color.getBlue()));
  double hue=0;
  double l=(max + min) / 2;
  double s=0;
  if (max == min) {
    hue=s=0;
  }
 else {
    double d=max - min;
    s=l > 0.5 ? d / (2 - max - min) : d / (max + min);
    if (max == color.getRed()) {
      hue=(color.getGreen() - color.getBlue()) / d + (color.getGreen() < color.getBlue() ? 6 : 0);
    }
 else     if (max == color.getGreen()) {
      hue=(color.getBlue() - color.getRed()) / d + 2;
    }
 else     if (max == color.getBlue()) {
      hue=(color.getRed() - color.getGreen()) / d + 4;
    }
    hue/=6;
  }
  currentHue=map(hue,0,1,0,255);
  double theta=map(currentHue,0,255,-Math.PI,Math.PI);
  double x=centerX + huesRadius * Math.cos(theta);
  double y=centerY + huesRadius * Math.sin(theta);
  colorsTransition=new CurveTransition(new Point2D(colorSelector.getTranslateX() + colorSelector.getPrefWidth() / 2,colorSelector.getTranslateY() + colorSelector.getPrefHeight() / 2),new Point2D(x,y));
  s=map(s,0,1,0,255);
  l=map(l,0,1,0,255);
  Point2D point=getPointFromSL((int)s,(int)l,slRadius);
  double pX=centerX - point.getX();
  double pY=centerY - point.getY();
  double endPointX;
  double endPointY;
  if (Math.pow(pX - centerX,2) + Math.pow(pY - centerY,2) < Math.pow(slRadius - 2,2)) {
    endPointX=pX - selector.getPrefWidth() / 2;
    endPointY=pY - selector.getPrefHeight() / 2;
  }
 else {
    double dx=pX - centerX;
    double dy=pY - centerY;
    theta=Math.atan2(dy,dx);
    x=centerX + (slRadius - 2) * Math.cos(theta);
    y=centerY + (slRadius - 2) * Math.sin(theta);
    endPointX=x - selector.getPrefWidth() / 2;
    endPointY=y - selector.getPrefHeight() / 2;
  }
  selectorTransition=new CachedTransition(selector,new Timeline(new KeyFrame(Duration.millis(1000),new KeyValue(selector.translateXProperty(),endPointX,Interpolator.EASE_BOTH),new KeyValue(selector.translateYProperty(),endPointY,Interpolator.EASE_BOTH)))){
{
      setCycleDuration(Duration.millis(160));
      setDelay(Duration.seconds(0));
    }
  }
;
  if (pTrans != null) {
    pTrans.stop();
  }
  pTrans=new ParallelTransition(colorsTransition,selectorTransition);
  pTrans.setOnFinished((finish) -> {
    if (pTrans.getStatus() == Status.STOPPED) {
      allowColorChange=true;
    }
  }
);
  pTrans.play();
  refreshHSLCircle();
}
