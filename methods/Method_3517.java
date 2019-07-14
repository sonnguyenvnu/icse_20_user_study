/** 
 * ??????
 * @param enable
 * @return
 */
public NShortSegment enablePlaceRecognize(boolean enable){
  config.placeRecognize=enable;
  config.updateNerConfig();
  return this;
}
