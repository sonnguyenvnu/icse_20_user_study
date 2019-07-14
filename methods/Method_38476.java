/** 
 * Creates form  {@link jodd.http.Buffer buffer} and sets few headers.
 */
protected Buffer formBuffer(){
  Buffer buffer=new Buffer();
  if (form == null || form.isEmpty()) {
    return buffer;
  }
  if (!isFormMultipart()) {
    String formEncoding=resolveFormEncoding();
    String formQueryString=HttpUtil.buildQuery(form,formEncoding);
    contentType("application/x-www-form-urlencoded",null);
    contentLength(formQueryString.length());
    buffer.append(formQueryString);
    return buffer;
  }
  String boundary=StringUtil.repeat('-',10) + RandomString.get().randomAlphaNumeric(10);
  for (  Map.Entry<String,?> entry : form) {
    buffer.append("--");
    buffer.append(boundary);
    buffer.append(CRLF);
    String name=entry.getKey();
    Object value=entry.getValue();
    if (value instanceof String) {
      String string=(String)value;
      buffer.append("Content-Disposition: form-data; name=\"").append(name).append('"').append(CRLF);
      buffer.append(CRLF);
      String formEncoding=resolveFormEncoding();
      String utf8String=StringUtil.convertCharset(string,formEncoding,StringPool.ISO_8859_1);
      buffer.append(utf8String);
    }
 else     if (value instanceof Uploadable) {
      Uploadable uploadable=(Uploadable)value;
      String fileName=uploadable.getFileName();
      if (fileName == null) {
        fileName=name;
      }
 else {
        String formEncoding=resolveFormEncoding();
        fileName=StringUtil.convertCharset(fileName,formEncoding,StringPool.ISO_8859_1);
      }
      buffer.append("Content-Disposition: form-data; name=\"").append(name);
      buffer.append("\"; filename=\"").append(fileName).append('"').append(CRLF);
      String mimeType=uploadable.getMimeType();
      if (mimeType == null) {
        mimeType=MimeTypes.getMimeType(FileNameUtil.getExtension(fileName));
      }
      buffer.append(HEADER_CONTENT_TYPE).append(": ").append(mimeType).append(CRLF);
      buffer.append("Content-Transfer-Encoding: binary").append(CRLF);
      buffer.append(CRLF);
      buffer.append(uploadable);
    }
 else {
      throw new HttpException("Unsupported type");
    }
    buffer.append(CRLF);
  }
  buffer.append("--").append(boundary).append("--");
  buffer.append(CRLF);
  contentType("multipart/form-data; boundary=" + boundary);
  contentLength(buffer.size());
  return buffer;
}
