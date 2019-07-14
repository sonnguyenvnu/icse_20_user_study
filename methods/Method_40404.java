public static Url newLibUrl(String path){
  if (!path.contains("#") && !path.endsWith(".html")) {
    path+=".html";
  }
  return new Url(LIBRARY_URL + path);
}
