public static InputStream readXmlInputStream(String uri){
  if (uri == null || "".equals(uri))   throw new IllegalArgumentException("Invalid uri. URI cannot be null or blank. ");
  try {
    URL url=new URL(uri);
    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Accept","application/xml");
    return connection.getInputStream();
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
