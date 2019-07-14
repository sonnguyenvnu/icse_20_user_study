/** 
 * @param link
 * @param encoding
 * @return
 */
public static String doGet(String link,String encoding,int connectTimeout,int readTimeout){
  HttpURLConnection conn=null;
  try {
    URL url=new URL(link);
    conn=(HttpURLConnection)url.openConnection();
    conn.setRequestMethod("GET");
    conn.setConnectTimeout(connectTimeout);
    conn.setReadTimeout(readTimeout);
    BufferedInputStream in=new BufferedInputStream(conn.getInputStream());
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    byte[] buf=new byte[1024];
    for (int i=0; (i=in.read(buf)) > 0; ) {
      out.write(buf,0,i);
    }
    out.flush();
    String s=new String(out.toByteArray(),encoding);
    return s;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return null;
  }
 finally {
    if (conn != null) {
      conn.disconnect();
      conn=null;
    }
  }
}
