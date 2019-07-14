private HttpURLConnection createConnection(URL url,String method,boolean doOutput){
  HttpURLConnection con;
  try {
    con=(HttpURLConnection)url.openConnection();
    con.setRequestMethod(method);
    con.setConnectTimeout(connectTimeout);
    con.setReadTimeout(readTimeout);
    con.setDoOutput(doOutput);
    con.setDoInput(true);
    con.setUseCaches(false);
    con.setRequestProperty("Content-Type","text/plain");
    return con;
  }
 catch (  IOException e) {
    e.printStackTrace();
    return null;
  }
}
