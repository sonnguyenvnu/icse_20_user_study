public String finish() throws IOException {
  StringBuilder b=new StringBuilder();
  writer.append("--" + boundary + "--").append(LINE_FEED);
  writer.close();
  int status=httpConn.getResponseCode();
  if (status == HttpURLConnection.HTTP_OK) {
    BufferedReader reader=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
    String line;
    while ((line=reader.readLine()) != null) {
      b.append(line);
    }
    reader.close();
    httpConn.disconnect();
  }
 else {
    try {
      BufferedReader reader=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
      String line;
      while ((line=reader.readLine()) != null) {
        b.append(line);
      }
      reader.close();
    }
 catch (    Throwable t) {
    }
    throw new IOException("Server returned status: " + status + " with body: " + b.toString() + " and Trailer header: " + httpConn.getHeaderFields().get("Trailer"));
  }
  return b.toString();
}
