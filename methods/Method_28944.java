private static String getContent(HttpURLConnection urlConn,String encoding){
  try {
    String responseContent=null;
    InputStream in=urlConn.getInputStream();
    BufferedReader rd=new BufferedReader(new InputStreamReader(in,encoding));
    String tempLine=rd.readLine();
    StringBuffer tempStr=new StringBuffer();
    String crlf=System.getProperty("line.separator");
    while (tempLine != null) {
      tempStr.append(tempLine);
      tempStr.append(crlf);
      tempLine=rd.readLine();
    }
    responseContent=tempStr.toString();
    rd.close();
    in.close();
    return responseContent;
  }
 catch (  Exception e) {
    throw new CacheCloudClientHttpUtilsException(e.getMessage(),e);
  }
}
