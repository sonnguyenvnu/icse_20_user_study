/** 
 * Sets content type. If charset is missing, current value is reset. If passed value is <code>null</code>, content type will be reset as never set.
 */
@Override public void setContentType(final String type){
  if (type == null) {
    mimeType=null;
    characterEncoding=null;
    return;
  }
  ContentTypeHeaderResolver contentTypeResolver=new ContentTypeHeaderResolver(type);
  mimeType=contentTypeResolver.getMimeType();
  characterEncoding=contentTypeResolver.getEncoding();
}
