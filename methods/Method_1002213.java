private String str(String... paths){
  String result=null;
  for (  String path : paths) {
    result=ParserUtils.extractString(path,mParser);
    if (result != null) {
      break;
    }
  }
  if (result == null) {
    Log.d(TAG,"Oops... seems that video metadata format has been changed: " + Arrays.toString(paths));
  }
  return result;
}
