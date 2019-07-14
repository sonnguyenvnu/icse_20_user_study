public static byte[] hexStringToByteArray(String s){
  String noSpaceString=s.replaceAll(" ","");
  byte[] data=decodeHex(noSpaceString);
  return data;
}
