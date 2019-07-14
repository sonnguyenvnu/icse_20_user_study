/** 
 * ??????????
 * @param enable
 */
public NShortSegment enableTranslatedNameRecognize(boolean enable){
  config.translatedNameRecognize=enable;
  config.updateNerConfig();
  return this;
}
