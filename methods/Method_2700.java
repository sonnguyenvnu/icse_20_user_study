private static List<String> extract(TFDictionary suffixTreeSet,int size){
  List<String> suffixList=new ArrayList<String>(size);
  for (  TermFrequency termFrequency : suffixTreeSet.values()) {
    if (suffixList.size() >= size)     break;
    suffixList.add(termFrequency.getKey());
  }
  return suffixList;
}
