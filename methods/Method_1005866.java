public static boolean equals(String value,String targetValue){
  if (value == null && targetValue == null) {
    return true;
  }
  if (value == null || targetValue == null) {
    return false;
  }
  return value.equals(targetValue);
}
