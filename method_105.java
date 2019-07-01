private static boolean _XXXXX_(Status status){
  return status.getCode() != Code.UNAVAILABLE && status.getCode() != Code.INTERNAL;
}