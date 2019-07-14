private List<List<Range>> getAllScientificNotations(){
  List<List<Range>> notations=new ArrayList<>();
  Pattern p=Pattern.compile("[+\\-]?(?:0|[1-9]\\d*)(?:\\.\\d*)?[eE][+\\-]?\\d+");
  for (  String code : codeTabs) {
    List<Range> notation=new ArrayList<>();
    Matcher m=p.matcher(code);
    while (m.find()) {
      notation.add(new Range(m.start(),m.end()));
    }
    notations.add(notation);
  }
  return notations;
}
