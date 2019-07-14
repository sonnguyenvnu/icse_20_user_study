public String rewrite(String text){
  List<Term> termList=StandardTokenizer.segment(text.toCharArray());
  StringBuilder sbOut=new StringBuilder((int)(text.length() * 1.2));
  String preWord=Predefine.TAG_BIGIN;
  for (  Term term : termList) {
    SynonymItem synonymItem=get(term.word);
    Synonym synonym;
    if (synonymItem != null && (synonym=synonymItem.randomSynonym(Type.EQUAL,preWord)) != null) {
      sbOut.append(synonym.realWord);
    }
 else     sbOut.append(term.word);
    preWord=PosTagCompiler.compile(term.nature.toString(),term.word);
  }
  return sbOut.toString();
}
