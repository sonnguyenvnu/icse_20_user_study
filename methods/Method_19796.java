@Override protected Properties readInternal(Class<? extends Properties> clazz,HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
  Properties properties=new Properties();
  HttpHeaders headers=inputMessage.getHeaders();
  MediaType contentType=headers.getContentType();
  Charset charset=null;
  if (contentType != null) {
    charset=contentType.getCharset();
  }
  charset=charset == null ? Charset.forName("UTF-8") : charset;
  InputStream body=inputMessage.getBody();
  InputStreamReader inputStreamReader=new InputStreamReader(body,charset);
  properties.load(inputStreamReader);
  return properties;
}
