/** 
 * CT_CHINESE?????????????????
 * @param sentence
 * @param normalized
 * @param start
 * @param end
 * @param preType
 * @param wordList
 */
private void pushPiece(String sentence,String normalized,int start,int end,byte preType,List<String> wordList){
  if (preType == CharType.CT_CHINESE) {
    segmenter.segment(sentence.substring(start,end),normalized.substring(start,end),wordList);
  }
 else {
    wordList.add(sentence.substring(start,end));
  }
}
