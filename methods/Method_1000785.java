public Map<String,Object> parse(HttpServletRequest req,UploadingContext context) throws UploadException {
  if (log.isDebugEnabled())   log.debug("FastUpload : " + Mvcs.getRequestPath(req));
  int bufferSize=context.getBufferSize();
  String charset=context.getCharset();
  FilePool tmps=context.getFilePool();
  int maxFileSize=context.getMaxFileSize();
  UploadInfo info=Uploads.createInfo(req);
  if (log.isDebugEnabled())   log.debug("info created");
  NutMap params=Uploads.createParamsMap(req);
  if (log.isDebugEnabled())   log.debugf("Params map created - %s params",params.size());
  String firstBoundary="--" + Http.multipart.getBoundary(req.getContentType());
  RemountBytes firstBoundaryBytes=RemountBytes.create(firstBoundary);
  String itemEndl="\r\n--" + Http.multipart.getBoundary(req.getContentType());
  RemountBytes itemEndlBytes=RemountBytes.create(itemEndl);
  RemountBytes nameEndlBytes=RemountBytes.create("\r\n\r\n");
  if (Http.multipart.getBoundary(req.getContentType()) == null) {
    if (log.isInfoEnabled())     log.info("boundary no found!!");
    return params;
  }
  if (log.isDebugEnabled())   log.debug("boundary: " + itemEndl);
  MarkMode mm;
  BufferRing br;
  try {
    ServletInputStream ins=req.getInputStream();
    br=new BufferRing(ins,3,bufferSize);
    info.current=br.load();
    mm=br.mark(firstBoundaryBytes);
    if (mm != MarkMode.FOUND) {
      if (log.isWarnEnabled())       log.warnf("Fail to find the firstBoundary (%s) in stream, quit!",firstBoundary);
      return params;
    }
    br.skipMark();
    if (log.isDebugEnabled())     log.debug("skip first boundary");
  }
 catch (  IOException e) {
    throw Lang.wrapThrow(e);
  }
  if (log.isDebugEnabled())   log.debug("Reading...");
  try {
    FieldMeta meta;
    do {
      info.current=br.load();
      mm=br.mark(nameEndlBytes);
      String s=br.dumpAsString(charset);
      if ("--".equals(s) || MarkMode.STREAM_END == mm) {
        break;
      }
 else       if (MarkMode.FOUND == mm) {
        meta=new FieldMeta(s);
      }
 else {
        throw new UploadInvalidFormatException("Fail to found nameEnd!");
      }
      if (log.isDebugEnabled())       log.debugf("Upload File info: FilePath=[%s],fieldName=[%s]",meta.getFileLocalPath(),meta.getName());
      if (meta.isFile()) {
        if (log.isDebugEnabled())         log.debugf("Upload Info: name=%s,content_type=%s",meta.getFileLocalName(),meta.getContentType());
        if (!context.isNameAccepted(meta.getFileLocalName())) {
          throw new UploadUnsupportedFileNameException(meta);
        }
        if (!context.isContentTypeAccepted(meta.getContentType())) {
          throw new UploadUnsupportedFileTypeException(meta);
        }
        if ("\"\"".equals(meta.getName()) || Strings.isBlank(meta.getFileLocalPath())) {
          do {
            info.current=br.load();
            mm=br.mark(itemEndlBytes);
            assertStreamNotEnd(mm);
            br.skipMark();
          }
 while (mm == MarkMode.NOT_FOUND);
        }
 else {
          File tmp=tmps.createFile(meta.getFileExtension());
          OutputStream ops=null;
          try {
            ops=new BufferedOutputStream(new FileOutputStream(tmp),bufferSize * 2);
            if (maxFileSize > 0) {
              long maxPos=info.current + maxFileSize;
              do {
                info.current=br.load();
                mm=br.mark(itemEndlBytes);
                assertStreamNotEnd(mm);
                if (info.current > maxPos) {
                  throw new UploadOutOfSizeException(meta);
                }
                br.dump(ops);
                if (info.stop)                 throw new UploadStopException(info);
              }
 while (mm == MarkMode.NOT_FOUND);
            }
 else {
              do {
                info.current=br.load();
                mm=br.mark(itemEndlBytes);
                assertStreamNotEnd(mm);
                br.dump(ops);
                if (info.stop)                 throw new UploadStopException(info);
              }
 while (mm == MarkMode.NOT_FOUND);
            }
          }
  finally {
            Streams.safeFlush(ops);
            Streams.safeClose(ops);
          }
          if (context.isIgnoreNull() && tmp.length() == 0) {
          }
 else {
            params.addv(meta.getName(),new TempFile(meta,tmp));
          }
        }
      }
 else {
        ByteArrayOutputStream bao=new ByteArrayOutputStream();
        do {
          info.current=br.load();
          mm=br.mark(itemEndlBytes);
          assertStreamNotEnd(mm);
          br.dump(bao);
        }
 while (mm == MarkMode.NOT_FOUND);
        String val=new String(bao.toByteArray(),charset);
        params.addv(meta.getName(),val);
        if (log.isDebugEnabled())         log.debugf("Found a param, name=[%s] value=[%s]",meta.getName(),val);
      }
    }
 while (mm != MarkMode.STREAM_END);
  }
 catch (  IOException e) {
    throw Lang.wrapThrow(e,UploadException.class);
  }
 finally {
    br.close();
  }
  info.current=info.sum;
  if (log.isDebugEnabled())   log.debugf("...Done %s bytes readed",br.readed());
  return params;
}
