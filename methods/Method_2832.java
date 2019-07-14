public List<PairFrequency> getPhraseByRe(){
  List<PairFrequency> pairFrequencyList=new ArrayList<PairFrequency>(entrySetPair.size());
  for (  Map.Entry<String,PairFrequency> entry : entrySetPair) {
    pairFrequencyList.add(entry.getValue());
  }
  Collections.sort(pairFrequencyList,new Comparator<PairFrequency>(){
    @Override public int compare(    PairFrequency o1,    PairFrequency o2){
      return -Double.compare(o1.re,o2.re);
    }
  }
);
  return pairFrequencyList;
}
