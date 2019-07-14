@NotNull public static Url newLibUrl(@NotNull String path){
  if (!path.contains("#") && !path.endsWith(".html")) {
    path+=".html";
  }
  return new Url(LIBRARY_URL + path);
}
