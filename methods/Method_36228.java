public String url(String path){
  if (!path.startsWith("/")) {
    path="/" + path;
  }
  return String.format("%s%s",baseUrl(),path);
}
