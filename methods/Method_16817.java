public static final State save(HttpServletRequest request,Map<String,Object> conf){
  FileItemStream fileStream=null;
  boolean isAjaxUpload=request.getHeader("X_Requested_With") != null;
  if (!ServletFileUpload.isMultipartContent(request)) {
    return new BaseState(false,AppInfo.NOT_MULTIPART_CONTENT);
  }
  ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
  if (isAjaxUpload) {
    upload.setHeaderEncoding("UTF-8");
  }
  try {
    FileItemIterator iterator=upload.getItemIterator(request);
    while (iterator.hasNext()) {
      fileStream=iterator.next();
      if (!fileStream.isFormField())       break;
      fileStream=null;
    }
    if (fileStream == null) {
      return new BaseState(false,AppInfo.NOTFOUND_UPLOAD_DATA);
    }
    String savePath=(String)conf.get("savePath");
    String originFileName=fileStream.getName();
    String suffix=FileType.getSuffixByFilename(originFileName);
    originFileName=originFileName.substring(0,originFileName.length() - suffix.length());
    if (!validType(suffix,(String[])conf.get("allowFiles"))) {
      return new BaseState(false,AppInfo.NOT_ALLOW_FILE_TYPE);
    }
    InputStream is=fileStream.openStream();
    try {
      FileService fileService=Context.FILE_SERVICE;
      String path=fileService.saveStaticFile(is,originFileName + suffix);
      State state=new BaseState(true);
      state.putInfo("size",0);
      state.putInfo("title",originFileName + suffix);
      state.putInfo("url",path);
      state.putInfo("type",suffix);
      state.putInfo("original",originFileName + suffix);
      return state;
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
 catch (  FileUploadException e) {
    return new BaseState(false,AppInfo.PARSE_REQUEST_ERROR);
  }
catch (  IOException e) {
    log.error(e.getMessage(),e);
  }
  return new BaseState(false,AppInfo.IO_ERROR);
}
