public static String parseFilePermission(File f){
  String per="";
  if (f.canRead()) {
    per=per + "r";
  }
  if (f.canWrite()) {
    per=per + "w";
  }
  if (f.canExecute()) {
    per=per + "x";
  }
  return per;
}
