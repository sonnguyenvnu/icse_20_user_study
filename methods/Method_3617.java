/** 
 * ????????attribute???
 * @param original
 * @param normalized
 * @param wordList
 * @return
 */
private List<CoreDictionary.Attribute> segmentWithAttribute(String original,String normalized,List<String> wordList){
  List<CoreDictionary.Attribute> attributeList;
  if (config.useCustomDictionary) {
    if (config.forceCustomDictionary) {
      attributeList=new LinkedList<CoreDictionary.Attribute>();
      segment(original,normalized,wordList,attributeList);
    }
 else {
      segmentAfterRule(original,normalized,wordList);
      attributeList=combineWithCustomDictionary(wordList);
    }
  }
 else {
    segmentAfterRule(original,normalized,wordList);
    attributeList=null;
  }
  return attributeList;
}
