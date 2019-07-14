private static String percentEncodeRfc3986(String string,Charset charset) throws UnsupportedEncodingException {
  try {
    string=string.replace("+","%2B");
    string=URLDecoder.decode(string,"UTF-8");
    string=URLEncoder.encode(string,charset.name());
    return string.replace("+","%20").replace("*","%2A").replace("%7E","~");
  }
 catch (  UnsupportedEncodingException|RuntimeException e) {
    if (haltOnError) {
      throw e;
    }
 else {
      return string;
    }
  }
}
