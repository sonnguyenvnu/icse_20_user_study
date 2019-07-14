public static String encodeParameters(List<HttpParameter> httpParams,String splitter,boolean quot){
  StringBuilder buf=new StringBuilder();
  for (  HttpParameter param : httpParams) {
    if (!param.isFile() && !param.isJson()) {
      if (buf.length() != 0) {
        if (quot) {
          buf.append("\"");
        }
        buf.append(splitter);
      }
      buf.append(HttpParameter.encode(param.getName())).append("=");
      if (quot) {
        buf.append("\"");
      }
      buf.append(HttpParameter.encode(param.getValue()));
    }
  }
  if (buf.length() != 0) {
    if (quot) {
      buf.append("\"");
    }
  }
  return buf.toString();
}
