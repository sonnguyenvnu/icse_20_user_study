static protected InputStream performQuery(String endpoint,String query) throws IOException {
  URL url=new URL(endpoint);
  URLConnection connection=url.openConnection();
  connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
  connection.setConnectTimeout(5000);
  connection.setDoOutput(true);
  DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
  try {
    String body="extend=" + ParsingUtilities.encode(query);
    dos.writeBytes(body);
  }
  finally {
    dos.flush();
    dos.close();
  }
  connection.connect();
  return connection.getInputStream();
}
