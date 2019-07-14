private PduPart[] loadParts(long msgId) throws MmsException {
  Cursor c=SqliteWrapper.query(mContext,mContentResolver,Uri.parse("content://mms/" + msgId + "/part"),PART_PROJECTION,null,null,null);
  PduPart[] parts=null;
  try {
    if ((c == null) || (c.getCount() == 0)) {
      if (LOCAL_LOGV) {
        Timber.v("loadParts(" + msgId + "): no part to load.");
      }
      return null;
    }
    int partCount=c.getCount();
    int partIdx=0;
    parts=new PduPart[partCount];
    while (c.moveToNext()) {
      PduPart part=new PduPart();
      Integer charset=getIntegerFromPartColumn(c,PART_COLUMN_CHARSET);
      if (charset != null) {
        part.setCharset(charset);
      }
      byte[] contentDisposition=getByteArrayFromPartColumn(c,PART_COLUMN_CONTENT_DISPOSITION);
      if (contentDisposition != null) {
        part.setContentDisposition(contentDisposition);
      }
      byte[] contentId=getByteArrayFromPartColumn(c,PART_COLUMN_CONTENT_ID);
      if (contentId != null) {
        part.setContentId(contentId);
      }
      byte[] contentLocation=getByteArrayFromPartColumn(c,PART_COLUMN_CONTENT_LOCATION);
      if (contentLocation != null) {
        part.setContentLocation(contentLocation);
      }
      byte[] contentType=getByteArrayFromPartColumn(c,PART_COLUMN_CONTENT_TYPE);
      if (contentType != null) {
        part.setContentType(contentType);
      }
 else {
        throw new MmsException("Content-Type must be set.");
      }
      byte[] fileName=getByteArrayFromPartColumn(c,PART_COLUMN_FILENAME);
      if (fileName != null) {
        part.setFilename(fileName);
      }
      byte[] name=getByteArrayFromPartColumn(c,PART_COLUMN_NAME);
      if (name != null) {
        part.setName(name);
      }
      long partId=c.getLong(PART_COLUMN_ID);
      Uri partURI=Uri.parse("content://mms/part/" + partId);
      part.setDataUri(partURI);
      String type=toIsoString(contentType);
      if (!ContentType.isImageType(type) && !ContentType.isAudioType(type) && !ContentType.isVideoType(type)) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        InputStream is=null;
        if (ContentType.TEXT_PLAIN.equals(type) || ContentType.APP_SMIL.equals(type) || ContentType.TEXT_HTML.equals(type)) {
          String text=c.getString(PART_COLUMN_TEXT);
          byte[] blob=new EncodedStringValue(text != null ? text : "").getTextString();
          baos.write(blob,0,blob.length);
        }
 else {
          try {
            is=mContentResolver.openInputStream(partURI);
            byte[] buffer=new byte[256];
            int len=is.read(buffer);
            while (len >= 0) {
              baos.write(buffer,0,len);
              len=is.read(buffer);
            }
          }
 catch (          IOException e) {
            Timber.e(e,"Failed to load part data");
            c.close();
            throw new MmsException(e);
          }
 finally {
            if (is != null) {
              try {
                is.close();
              }
 catch (              IOException e) {
                Timber.e(e,"Failed to close stream");
              }
            }
          }
        }
        part.setData(baos.toByteArray());
      }
      parts[partIdx++]=part;
    }
  }
  finally {
    if (c != null) {
      c.close();
    }
  }
  return parts;
}
