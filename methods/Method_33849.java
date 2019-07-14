public static String getHeader(String httpMethod){
  long sen=System.currentTimeMillis() / 1000;
  String h=getUuid() + getdevice() + CLIENT_TYPE + httpMethod + "0" + getVersion() + sen;
  return h;
}
