/** 
 * Loads the content of this page from a fetched HttpEntity.
 * @param entity HttpEntity
 * @param maxBytes The maximum number of bytes to read
 * @throws IOException when load fails
 */
public void load(HttpEntity entity,int maxBytes) throws IOException {
  contentType=null;
  Header type=entity.getContentType();
  if (type != null) {
    contentType=type.getValue();
  }
  contentEncoding=null;
  Header encoding=entity.getContentEncoding();
  if (encoding != null) {
    contentEncoding=encoding.getValue();
  }
  Charset charset;
  try {
    charset=ContentType.getOrDefault(entity).getCharset();
  }
 catch (  Exception e) {
    logger.warn("parse charset failed: {}",e.getMessage());
    charset=Charset.forName("UTF-8");
  }
  if (charset != null) {
    contentCharset=charset.displayName();
  }
  contentData=toByteArray(entity,maxBytes);
}
