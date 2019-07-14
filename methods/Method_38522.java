/** 
 * Unzips GZip-ed body content, removes the content-encoding header and sets the new content-length value.
 */
public HttpResponse unzip(){
  String contentEncoding=contentEncoding();
  if (contentEncoding != null && contentEncoding().equals("gzip")) {
    if (body != null) {
      headerRemove(HEADER_CONTENT_ENCODING);
      try {
        ByteArrayInputStream in=new ByteArrayInputStream(body.getBytes(StringPool.ISO_8859_1));
        GZIPInputStream gzipInputStream=new GZIPInputStream(in);
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        StreamUtil.copy(gzipInputStream,out);
        body(out.toString(StringPool.ISO_8859_1));
      }
 catch (      IOException ioex) {
        throw new HttpException(ioex);
      }
    }
  }
  return this;
}
