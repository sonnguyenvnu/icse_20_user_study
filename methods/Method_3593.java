@Override public List<String> getKeywords(List<Term> termList,int size){
  Set<Map.Entry<String,Float>> entrySet=top(size,getTermAndRank(termList)).entrySet();
  List<String> result=new ArrayList<String>(entrySet.size());
  for (  Map.Entry<String,Float> entry : entrySet) {
    result.add(entry.getKey());
  }
  return result;
}
