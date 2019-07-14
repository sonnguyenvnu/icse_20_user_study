private HttpURLConnection downloadFrom(Uri uri,int maxRedirects) throws IOException {
  HttpURLConnection connection=openConnectionTo(uri);
  connection.setConnectTimeout(mHttpConnectionTimeout);
  int responseCode=connection.getResponseCode();
  if (isHttpSuccess(responseCode)) {
    return connection;
  }
 else   if (isHttpRedirect(responseCode)) {
    String nextUriString=connection.getHeaderField("Location");
    connection.disconnect();
    Uri nextUri=(nextUriString == null) ? null : Uri.parse(nextUriString);
    String originalScheme=uri.getScheme();
    if (maxRedirects > 0 && nextUri != null && !nextUri.getScheme().equals(originalScheme)) {
      return downloadFrom(nextUri,maxRedirects - 1);
    }
 else {
      String message=maxRedirects == 0 ? error("URL %s follows too many redirects",uri.toString()) : error("URL %s returned %d without a valid redirect",uri.toString(),responseCode);
      throw new IOException(message);
    }
  }
 else {
    connection.disconnect();
    throw new IOException(String.format("Image URL %s returned HTTP code %d",uri.toString(),responseCode));
  }
}
