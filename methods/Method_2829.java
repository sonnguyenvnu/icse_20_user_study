public void addAll(String[] termList){
  for (  String term : termList) {
    addTerm(term);
  }
  String first=null;
  for (  String current : termList) {
    if (first != null) {
      addPair(first,current);
    }
    first=current;
  }
  for (int i=2; i < termList.length; ++i) {
    addTria(termList[i - 2],termList[i - 1],termList[i]);
  }
}
