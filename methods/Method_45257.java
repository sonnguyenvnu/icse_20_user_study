@SuppressWarnings("unchecked") private String targetFileName(final Request request){
  String filename=this.filename(request);
  if (config != null) {
    return (String)config.apply(filename);
  }
  return filename;
}
