public static String calMD5Checksum(String filename){
  byte[] b;
  b=createChecksum(filename);
  String digestInHex=DatatypeConverter.printHexBinary(b).toUpperCase();
  return digestInHex;
}
