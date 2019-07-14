public static String getEntityAsStringAndCloseStream(HttpResponse httpResponse){
  HttpEntity entity=httpResponse.getEntity();
  if (entity != null) {
    try {
      String content=EntityUtils.toString(entity,UTF_8.name());
      entity.getContent().close();
      return content;
    }
 catch (    IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }
  return null;
}
