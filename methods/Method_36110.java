@Override public boolean isMultipart(){
  String header=getHeader("Content-Type");
  return (header != null && header.contains("multipart/form-data"));
}
