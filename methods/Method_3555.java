/** 
 * ??????????
 * @param enable
 */
public Segment enableJapaneseNameRecognize(boolean enable){
  config.japaneseNameRecognize=enable;
  config.updateNerConfig();
  return this;
}
