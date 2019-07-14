public void addAll(List<Term> resultList){
  String[] termList=new String[resultList.size()];
  int i=0;
  for (  Term word : resultList) {
    termList[i]=word.word;
    ++i;
  }
  addAll(termList);
}
