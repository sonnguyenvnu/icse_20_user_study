/** 
 * ??????????
 * @param enable
 */
public Segment enableTranslatedNameRecognize(boolean enable){
  config.translatedNameRecognize=enable;
  config.updateNerConfig();
  return this;
}
