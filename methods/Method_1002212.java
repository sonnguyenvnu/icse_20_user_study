private Boolean bool(String... paths){
  Boolean result=null;
  for (  String path : paths) {
    result=ParserUtils.extractBool(path,mParser);
    if (result != null) {
      break;
    }
  }
  if (result == null) {
    Log.d(TAG,"Oops... seems that video metadata format has been changed: " + Arrays.toString(paths));
  }
  return result;
}
