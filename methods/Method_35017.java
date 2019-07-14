public static char saturatedCast(long value){
  if (value > Character.MAX_VALUE) {
    return Character.MAX_VALUE;
  }
  if (value < Character.MIN_VALUE) {
    return Character.MIN_VALUE;
  }
  return (char)value;
}
