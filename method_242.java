@Override public String _XXXXX_(){
  String commentString="";
  try {
    commentString=getSegmentDataAsString("UTF-8");
  }
 catch (  final UnsupportedEncodingException cannotHappen) {
  }
  return "COM (" + commentString + ")";
}