static boolean containsFile(List<HttpParameter> params){
  boolean containsFile=false;
  for (  HttpParameter param : params) {
    if (param.isFile()) {
      containsFile=true;
      break;
    }
  }
  return containsFile;
}
