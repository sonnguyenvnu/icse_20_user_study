public Map<String,List<FileItem>> parseParameterMap(IHTTPSession session) throws FileUploadException {
  return this.parseParameterMap(new NanoHttpdContext(session));
}
