public static String abbreviate(final String str,final int maxWidth){
  if (str.length() <= maxWidth) {
    return str;
  }
  final String abrevMarker="...";
  return str.substring(0,maxWidth - 3) + abrevMarker;
}
