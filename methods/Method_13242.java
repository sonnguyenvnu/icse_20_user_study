public boolean accept(API api,URI uri){
  return "file".equals(uri.getScheme());
}
