/** 
 * @param requestUrl ????
 * @param method ?????GET?POST?
 * @param outputStr ?????
 * @throws Exception
 */
public static HttpResponse httpsRequest(String requestUrl,String method,String outputStr) throws IOException {
  HttpsURLConnection connection=null;
  try {
    SSLSocketFactory ssf=null;
    try {
      TrustManager[] tm={new MyX509TrustManager()};
      SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
      sslContext.init(null,tm,new java.security.SecureRandom());
      ssf=sslContext.getSocketFactory();
    }
 catch (    NoSuchAlgorithmException e) {
      throw new IOException("???SSLContext??",e);
    }
catch (    NoSuchProviderException e) {
      throw new IOException("???SSLContext??",e);
    }
catch (    KeyManagementException e) {
      throw new IOException("???SSLContext??",e);
    }
    URL url=new URL(requestUrl);
    connection=(HttpsURLConnection)url.openConnection();
    connection.setSSLSocketFactory(ssf);
    connection.setDoOutput(true);
    connection.setDoInput(true);
    connection.setUseCaches(false);
    connection.setRequestMethod(method);
    if ("GET".equalsIgnoreCase(method)) {
      connection.connect();
    }
    if (null != outputStr) {
      OutputStream outputStream=connection.getOutputStream();
      outputStream.write(outputStr.getBytes("UTF-8"));
      outputStream.close();
    }
    return new HttpResponse(connection);
  }
 catch (  IOException e) {
    if (connection != null) {
      connection.disconnect();
    }
    throw e;
  }
}
