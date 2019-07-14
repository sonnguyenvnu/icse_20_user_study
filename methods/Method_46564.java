public static String getPath(URL theURL){
  String file=theURL.getFile();
  String path=null;
  if (file != null) {
    int q=file.lastIndexOf('?');
    if (q != -1) {
      path=file.substring(0,q);
    }
 else {
      path=file;
    }
  }
  return path;
}
