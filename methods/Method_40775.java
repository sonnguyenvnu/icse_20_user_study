public static File makePath(String... files){
  File ret=new File(files[0]);
  for (int i=1; i < files.length; i++) {
    ret=new File(ret,files[i]);
  }
  return ret;
}
