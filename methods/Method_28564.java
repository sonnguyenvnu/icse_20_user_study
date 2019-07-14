public static String[] getLineNo(@Nullable String url){
  String lineNo[]=null;
  if (url != null) {
    try {
      Uri uri=Uri.parse(url);
      String lineNumber=uri.getEncodedFragment();
      if (lineNumber != null) {
        lineNo=lineNumber.replaceAll("L","").split("-");
      }
    }
 catch (    Exception ignored) {
    }
  }
  return lineNo;
}
