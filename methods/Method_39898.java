/** 
 * Strips a servlet session ID from <code>url</code>.  The session ID is encoded as a URL "path parameter" beginning with "jsessionid=". We thus remove anything we find between ";jsessionid=" (inclusive) and either EOS or a subsequent ';' (exclusive).
 */
public static String stripSessionId(final String url){
  StringBuilder u=new StringBuilder(url);
  int sessionStart;
  while ((sessionStart=u.toString().indexOf(";jsessionid=")) != -1) {
    int sessionEnd=u.toString().indexOf(';',sessionStart + 1);
    if (sessionEnd == -1) {
      sessionEnd=u.toString().indexOf('?',sessionStart + 1);
    }
    if (sessionEnd == -1) {
      sessionEnd=u.length();
    }
    u.delete(sessionStart,sessionEnd);
  }
  return u.toString();
}
