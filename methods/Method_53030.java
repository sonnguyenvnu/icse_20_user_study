public static boolean containsFile(HttpParameter[] params){
  boolean containsFile=false;
  if (null == params) {
    return false;
  }
  for (  HttpParameter param : params) {
    if (param.isFile()) {
      containsFile=true;
      break;
    }
  }
  return containsFile;
}
