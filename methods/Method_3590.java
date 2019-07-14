/** 
 * ????????????rank
 * @param content
 * @return
 */
public Map<String,Float> getTermAndRank(String content){
  assert content != null;
  List<Term> termList=defaultSegment.seg(content);
  return getTermAndRank(termList);
}
