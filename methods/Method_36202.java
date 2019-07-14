private String wrap(String s){
  String safeString=s == null ? "" : s;
  return Strings.wrapIfLongestLineExceedsLimit(safeString,getColumnWidth());
}
