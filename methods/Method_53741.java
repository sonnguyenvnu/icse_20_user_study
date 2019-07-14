private static Long validateInteger(String intValue){
  Long l=Long.decode(intValue);
  if ((1L << 32) - 1 < l) {
    throw new NumberFormatException("long value");
  }
  return l;
}
