public static String unescape(final String part){
  final String result=part.replace("~1","/").replace("~0","~");
  return result.replace("\\.",".");
}
