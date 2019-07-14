/** 
 * ??????????????
 * @return List<ISegmenter>
 */
private List<ISegmenter> loadSegmenters(){
  List<ISegmenter> segmenters=new ArrayList<ISegmenter>(4);
  segmenters.add(new LetterSegmenter());
  segmenters.add(new CN_QuantifierSegmenter());
  segmenters.add(new CJKSegmenter());
  return segmenters;
}
