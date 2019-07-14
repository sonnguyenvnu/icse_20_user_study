public String[] segment(String text){
  char[] charArray=text.toCharArray();
  List<Term> termList=NotionalTokenizer.segment(charArray);
  ListIterator<Term> listIterator=termList.listIterator();
  while (listIterator.hasNext()) {
    Term term=listIterator.next();
    if (term.word.indexOf('\u0000') >= 0) {
      listIterator.remove();
    }
  }
  String[] termArray=new String[termList.size()];
  int p=-1;
  for (  Term term : termList) {
    termArray[++p]=term.word;
  }
  return termArray;
}
