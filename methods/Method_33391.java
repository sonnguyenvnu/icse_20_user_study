/** 
 * creates error animation frames when moving from large -> small error container
 * @param errorContainerHeight
 * @return
 */
private KeyFrame createSmallerScaleFrame(double errorContainerHeight){
  return new KeyFrame(Duration.millis(100),new KeyValue(errorClipScale.yProperty(),errorContainerHeight / getHeight(),Interpolator.EASE_BOTH));
}
