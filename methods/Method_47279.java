/** 
 * URL-encodes everything between "/"-characters. Encodes spaces as '%20' instead of '+'.
 */
private String encodeUri(String uri){
  String newUri="";
  StringTokenizer st=new StringTokenizer(uri,"/ ",true);
  while (st.hasMoreTokens()) {
    String tok=st.nextToken();
    if (tok.equals("/"))     newUri+="/";
 else     if (tok.equals(" "))     newUri+="%20";
 else {
      newUri+=URLEncoder.encode(tok);
    }
  }
  return newUri;
}
