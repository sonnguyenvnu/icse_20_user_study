private String debugHiddenTokens(antlr.CommonHiddenStreamToken t){
  final StringBuilder sb=new StringBuilder();
  for (; t != null; t=filter.getHiddenAfter(t)) {
    if (sb.length() == 0) {
      sb.append("[");
    }
    sb.append(t.getText().replace("\n","\\n"));
  }
  if (sb.length() > 0) {
    sb.append("]");
  }
  return sb.toString();
}
