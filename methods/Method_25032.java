/** 
 * URL-encodes everything between "/"-characters. Encodes spaces as '%20' instead of '+'.
 */
private String encodeUri(String uri){
  String newUri="";
  StringTokenizer st=new StringTokenizer(uri,"/ ",true);
  while (st.hasMoreTokens()) {
    String tok=st.nextToken();
    if ("/".equals(tok)) {
      newUri+="/";
    }
 else     if (" ".equals(tok)) {
      newUri+="%20";
    }
 else {
      try {
        newUri+=URLEncoder.encode(tok,"UTF-8");
      }
 catch (      UnsupportedEncodingException ignored) {
      }
    }
  }
  return newUri;
}
