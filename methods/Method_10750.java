private static String gpsInfoConvert(double gpsInfo){
  gpsInfo=Math.abs(gpsInfo);
  String dms=Location.convert(gpsInfo,Location.FORMAT_SECONDS);
  String[] splits=dms.split(":");
  String[] secnds=(splits[2]).split("\\.");
  String seconds;
  if (secnds.length == 0) {
    seconds=splits[2];
  }
 else {
    seconds=secnds[0];
  }
  return splits[0] + "/1," + splits[1] + "/1," + seconds + "/1";
}
