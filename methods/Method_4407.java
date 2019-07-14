private static long getDurationRemainingSec(Map<String,String> keyStatus,String property){
  if (keyStatus != null) {
    try {
      String value=keyStatus.get(property);
      if (value != null) {
        return Long.parseLong(value);
      }
    }
 catch (    NumberFormatException e) {
    }
  }
  return C.TIME_UNSET;
}
