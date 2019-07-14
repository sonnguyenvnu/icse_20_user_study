/** 
 * ??????
 * @param enable
 * @return
 */
public Segment enableNameRecognize(boolean enable){
  config.nameRecognize=enable;
  config.updateNerConfig();
  return this;
}
