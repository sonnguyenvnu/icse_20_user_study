/** 
 * ??????????
 * @param enable
 */
public NShortSegment enableJapaneseNameRecognize(boolean enable){
  config.japaneseNameRecognize=enable;
  config.updateNerConfig();
  return this;
}
