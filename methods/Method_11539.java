public static HttpRequestBody xml(String xml,String encoding){
  try {
    return new HttpRequestBody(xml.getBytes(encoding),ContentType.XML,encoding);
  }
 catch (  UnsupportedEncodingException e) {
    throw new IllegalArgumentException("illegal encoding " + encoding,e);
  }
}
