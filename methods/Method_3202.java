private static List<String> convert(List<Term> termList){
  List<String> words=new ArrayList<String>(termList.size());
  for (  Term term : termList) {
    words.add(term.word);
  }
  return words;
}
