/** 
 * ??????
 * @param enable
 * @return
 */
public Segment enablePlaceRecognize(boolean enable){
  config.placeRecognize=enable;
  config.updateNerConfig();
  return this;
}
