private static List<Term> segSentence(String text){
  String sText=CharTable.convert(text);
  List<Term> termList=SEGMENT.seg(sText);
  int offset=0;
  for (  Term term : termList) {
    term.offset=offset;
    term.word=text.substring(offset,offset + term.length());
    offset+=term.length();
  }
  return termList;
}
