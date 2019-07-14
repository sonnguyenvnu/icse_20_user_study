/** 
 * ????????????
 * @param termList
 * @param size
 * @return
 */
@Override public List<String> getKeywords(List<Term> termList,int size){
  clear();
  add(termList);
  Collection<TermFrequency> topN=top(size);
  List<String> r=new ArrayList<String>(topN.size());
  for (  TermFrequency termFrequency : topN) {
    r.add(termFrequency.getTerm());
  }
  return r;
}
