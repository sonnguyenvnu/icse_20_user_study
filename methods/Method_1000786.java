public Map<String,Object> parse(HttpServletRequest req,UploadingContext context) throws UploadException, UploadOutOfSizeException, UploadUnsupportedFileNameException, UploadUnsupportedFileTypeException {
  int bufferSize=context.getBufferSize();
  int size=req.getContentLength();
  int maxSize=context.getMaxFileSize();
  FilePool tmps=context.getFilePool();
  FieldMeta meta=new FieldMeta("name=\"filedata\"; filename=\"nutz.jpg\"");
  if (maxSize > 0 && size > context.getMaxFileSize()) {
    throw new UploadOutOfSizeException(meta);
  }
  String disposition=req.getHeader("Content-Disposition");
  if (disposition != null && disposition.startsWith("attachment;")) {
    meta=new FieldMeta(disposition.substring("attachment;".length()).trim());
  }
 else {
    if (log.isInfoEnabled())     log.info("Content-Disposition no found, using default fieldname=filedata, filename=nutz.jpg");
  }
  if (log.isDebugEnabled())   log.debugf("Upload File info: FilePath=[%s],fieldName=[%s]",meta.getFileLocalPath(),meta.getName());
  if (!context.isNameAccepted(meta.getFileLocalName())) {
    throw new UploadUnsupportedFileNameException(meta);
  }
  File tmp=tmps.createFile(meta.getFileExtension());
  OutputStream ops=null;
  try {
    ops=new BufferedOutputStream(new FileOutputStream(tmp),bufferSize * 2);
    Streams.writeAndClose(ops,req.getInputStream());
    if (tmp.length() != size)     throw new UploadOutOfSizeException(meta);
    if (maxSize > 0 && tmp.length() > maxSize)     throw new UploadOutOfSizeException(meta);
    NutMap params=Uploads.createParamsMap(req);
    if (tmp.length() == 0 && context.isIgnoreNull()) {
      if (log.isDebugEnabled())       log.debug("emtry file , drop it ~~");
      tmp.delete();
    }
 else {
      params.put(meta.getName(),new TempFile(meta,tmp));
    }
    return params;
  }
 catch (  FileNotFoundException e) {
    throw Lang.wrapThrow(e);
  }
catch (  IOException e) {
    throw Lang.wrapThrow(e);
  }
 finally {
    Streams.safeClose(ops);
  }
}
