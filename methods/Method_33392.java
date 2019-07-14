/** 
 * creates error animation frames when moving from small -> large error container
 * @return
 */
private KeyFrame createScaleToOneFrames(){
  return new KeyFrame(Duration.millis(100),new KeyValue(errorClipScale.yProperty(),1,Interpolator.EASE_BOTH));
}
