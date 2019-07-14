public Uri persistPart(PduPart part,long msgId,HashMap<Uri,InputStream> preOpenedFiles) throws MmsException {
  Uri uri=Uri.parse("content://mms/" + msgId + "/part");
  ContentValues values=new ContentValues(8);
  int charset=part.getCharset();
  if (charset != 0) {
    values.put(Part.CHARSET,charset);
  }
  String contentType=getPartContentType(part);
  if (contentType != null) {
    if (ContentType.IMAGE_JPG.equals(contentType)) {
      contentType=ContentType.IMAGE_JPEG;
    }
    values.put(Part.CONTENT_TYPE,contentType);
    if (ContentType.APP_SMIL.equals(contentType)) {
      values.put(Part.SEQ,-1);
    }
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
  Uri res=SqliteWrapper.insert(mContext,mContentResolver,uri,values);
  if (res == null) {
    throw new MmsException("Failed to persist part, return null.");
  }
  persistData(part,res,contentType,preOpenedFiles);
  part.setDataUri(res);
  return res;
}
