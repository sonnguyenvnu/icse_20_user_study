private int computeHttpResponse(String url){
  try {
    final HttpURLConnection httpURLConnection=(HttpURLConnection)new URL(url).openConnection();
    httpURLConnection.setRequestMethod("HEAD");
    httpURLConnection.setConnectTimeout(5000);
    httpURLConnection.setReadTimeout(15000);
    httpURLConnection.connect();
    final int responseCode=httpURLConnection.getResponseCode();
    String response="HTTP " + responseCode;
    if (httpURLConnection.getHeaderField("Location") != null) {
      response+=", Location: " + httpURLConnection.getHeaderField("Location");
    }
    LOG.fine("response: " + response + " on " + url);
    return responseCode;
  }
 catch (  IOException ex) {
    LOG.fine("response: " + ex.getClass().getName() + " on " + url + " : " + ex.getMessage());
    return 599;
  }
}
