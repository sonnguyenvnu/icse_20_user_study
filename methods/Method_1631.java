@VisibleForTesting static byte[] getData(String uri){
  Preconditions.checkArgument(uri.substring(0,5).equals("data:"));
  int commaPos=uri.indexOf(',');
  String dataStr=uri.substring(commaPos + 1,uri.length());
  if (isBase64(uri.substring(0,commaPos))) {
    return Base64.decode(dataStr,Base64.DEFAULT);
  }
 else {
    String str=Uri.decode(dataStr);
    byte[] b=str.getBytes();
    return b;
  }
}
