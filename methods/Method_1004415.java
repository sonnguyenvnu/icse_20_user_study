private String request(){
  InputStreamReader in=null;
  HttpURLConnection connection=null;
  try {
    connection=(HttpURLConnection)(new URL(metaServerEndpoint).openConnection());
    connection.setConnectTimeout(1000);
    connection.setReadTimeout(500);
    connection.setDoInput(true);
    in=new InputStreamReader(connection.getInputStream());
    String content=CharStreams.toString(in);
    if (connection.getResponseCode() != 200) {
      return null;
    }
    return content.trim();
  }
 catch (  IOException e) {
    if (connection == null || connection.getErrorStream() == null) {
      return null;
    }
    InputStreamReader errIn=null;
    try {
      errIn=new InputStreamReader(connection.getErrorStream());
      String error=CharStreams.toString(errIn);
      LOG.debug("read error stream {}",error);
    }
 catch (    IOException e1) {
      LOG.debug("read error stream failed",e1);
    }
 finally {
      closeQuietly(errIn);
    }
    return null;
  }
catch (  Exception e) {
    return null;
  }
 finally {
    closeQuietly(in);
  }
}
