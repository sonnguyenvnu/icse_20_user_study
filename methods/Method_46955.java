public static InetAddress intToInet(int value){
  byte[] bytes=new byte[4];
  for (int i=0; i < 4; i++) {
    bytes[i]=byteOfInt(value,i);
  }
  try {
    return InetAddress.getByAddress(bytes);
  }
 catch (  UnknownHostException e) {
    return null;
  }
}
