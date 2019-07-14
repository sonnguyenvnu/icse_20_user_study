private void initAnimation(Orientation orientation){
  double thumbPos, thumbNewPos;
  DoubleProperty layoutProperty;
  if (orientation == Orientation.HORIZONTAL) {
    if (((JFXSlider)getSkinnable()).getIndicatorPosition() == IndicatorPosition.RIGHT) {
      thumbPos=thumb.getLayoutY() - thumb.getHeight();
      thumbNewPos=thumbPos - shifting;
    }
 else {
      double height=animatedThumb.prefHeight(animatedThumb.prefWidth(-1));
      thumbPos=thumb.getLayoutY() - height / 2;
      thumbNewPos=thumb.getLayoutY() - height - thumb.getHeight();
    }
    layoutProperty=animatedThumb.translateYProperty();
  }
 else {
    if (((JFXSlider)getSkinnable()).getIndicatorPosition() == IndicatorPosition.RIGHT) {
      thumbPos=thumb.getLayoutX() - thumb.getWidth();
      thumbNewPos=thumbPos - shifting;
    }
 else {
      double width=animatedThumb.prefWidth(-1);
      thumbPos=thumb.getLayoutX() - width / 2;
      thumbNewPos=thumb.getLayoutX() - width - thumb.getWidth();
    }
    layoutProperty=animatedThumb.translateXProperty();
  }
  clearAnimation();
  timeline=new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(animatedThumb.scaleXProperty(),0,Interpolator.EASE_BOTH),new KeyValue(animatedThumb.scaleYProperty(),0,Interpolator.EASE_BOTH),new KeyValue(layoutProperty,thumbPos,Interpolator.EASE_BOTH)),new KeyFrame(Duration.seconds(0.2),new KeyValue(animatedThumb.scaleXProperty(),1,Interpolator.EASE_BOTH),new KeyValue(animatedThumb.scaleYProperty(),1,Interpolator.EASE_BOTH),new KeyValue(layoutProperty,thumbNewPos,Interpolator.EASE_BOTH)));
}
