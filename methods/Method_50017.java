private void updatePart(Uri uri,PduPart part,HashMap<Uri,InputStream> preOpenedFiles) throws MmsException {
  ContentValues values=new ContentValues(7);
  int charset=part.getCharset();
  if (charset != 0) {
    values.put(Part.CHARSET,charset);
  }
  String contentType=null;
  if (part.getContentType() != null) {
    contentType=toIsoString(part.getContentType());
    values.put(Part.CONTENT_TYPE,contentType);
  }
 else {
    throw new MmsException("MIME type of the part must be set.");
  }
  if (part.getFilename() != null) {
    String fileName=new String(part.getFilename());
    values.put(Part.FILENAME,fileName);
  }
  if (part.getName() != null) {
    String name=new String(part.getName());
    values.put(Part.NAME,name);
  }
  Object value=null;
  if (part.getContentDisposition() != null) {
    value=toIsoString(part.getContentDisposition());
    values.put(Part.CONTENT_DISPOSITION,(String)value);
  }
  if (part.getContentId() != null) {
    value=toIsoString(part.getContentId());
    values.put(Part.CONTENT_ID,(String)value);
  }
  if (part.getContentLocation() != null) {
    value=toIsoString(part.getContentLocation());
    values.put(Part.CONTENT_LOCATION,(String)value);
  }
  SqliteWrapper.update(mContext,mContentResolver,uri,values,null,null);
  if ((part.getData() != null) || (uri != part.getDataUri())) {
    persistData(part,uri,contentType,preOpenedFiles);
  }
}
