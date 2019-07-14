public static String[] serializeHeaders(Header[] headers){
  if (headers == null) {
    return new String[0];
  }
  String[] rtn=new String[headers.length * 2];
  int index=-1;
  for (  Header h : headers) {
    rtn[++index]=h.getName();
    rtn[++index]=h.getValue();
  }
  return rtn;
}
