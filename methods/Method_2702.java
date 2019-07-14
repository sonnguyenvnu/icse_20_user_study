private static String combine(List<Term> termList){
  StringBuilder sbResult=new StringBuilder();
  for (  Term term : termList) {
    sbResult.append(term.word);
  }
  return sbResult.toString();
}
