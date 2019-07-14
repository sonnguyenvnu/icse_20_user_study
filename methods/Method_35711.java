public static byte[] getEntityAsByteArrayAndCloseStream(HttpResponse httpResponse){
  HttpEntity entity=httpResponse.getEntity();
  if (entity != null) {
    try {
      byte[] content=EntityUtils.toByteArray(entity);
      entity.getContent().close();
      return content;
    }
 catch (    IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }
  return null;
}
