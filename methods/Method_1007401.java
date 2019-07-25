@Override public boolean exists(){
  try {
    URL url=getURL();
    if (ResourceUtils.isFileURL(url)) {
      return getFile().exists();
    }
 else {
      URLConnection con=url.openConnection();
      customizeConnection(con);
      HttpURLConnection httpCon=(con instanceof HttpURLConnection ? (HttpURLConnection)con : null);
      if (httpCon != null) {
        int code=httpCon.getResponseCode();
        if (code == HttpURLConnection.HTTP_OK) {
          return true;
        }
 else         if (code == HttpURLConnection.HTTP_NOT_FOUND) {
          return false;
        }
      }
      if (con.getContentLength() >= 0) {
        return true;
      }
      if (httpCon != null) {
        httpCon.disconnect();
        return false;
      }
 else {
        try (InputStream is=getInputStream()){
          return true;
        }
       }
    }
  }
 catch (  IOException ex) {
    return false;
  }
}
