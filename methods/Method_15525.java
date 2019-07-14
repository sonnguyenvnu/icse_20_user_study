@Override public String getAlias(){
  if (StringUtil.isEmpty(alias,true)) {
    alias=getTable();
  }
  String q=getQuote();
  return q + alias + q;
}
