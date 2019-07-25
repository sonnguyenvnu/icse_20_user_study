@Override public LineNumberNode parse(String text){
  RegexToken matcher=token();
  MatchResult result=matcher.matches(text);
  if (result.isSuccess()) {
    int lineno=matcher.getMatch("LINENO");
    String lblName=matcher.get("LABEL");
    return new NamedLineNumberNode(lineno,null,lblName);
  }
 else   if (matcher.has("LINENO")) {
    int lineno=matcher.getMatch("LINENO");
    return new LazyLineNumberNode(lineno);
  }
  return fail(text,"Expected: <LINE_NO> <LABEL_TITLE>");
}
