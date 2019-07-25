private String request(final String httpUrl) throws IOException {
  final URL url=new URL(httpUrl);
  final HttpURLConnection conn=(HttpURLConnection)url.openConnection();
  conn.setRequestMethod("GET");
  conn.setConnectTimeout(5000);
  conn.setReadTimeout(5000);
  try (final BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()))){
    final StringBuilder sb=new StringBuilder();
    while (true) {
      final String line=reader.readLine();
      if (line == null) {
        break;
      }
      sb.append(line);
    }
    return sb.toString();
  }
  finally {
    conn.disconnect();
  }
}
