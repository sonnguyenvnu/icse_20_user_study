public static String strip(String id){
  int pos=id.lastIndexOf('.');
  if (pos != -1) {
    return id.substring(0,pos);
  }
  return id;
}
