private String showSpeed(double speed){
  String speedString;
  if (speed >= 1048576d) {
    speedString=showFloatFormat.format(speed / 1048576d) + "MB/s";
  }
 else {
    speedString=showFloatFormat.format(speed / 1024d) + "KB/s";
  }
  return speedString;
}
