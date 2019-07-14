public static boolean validate(final Object value,final int max){
  if (value == null) {
    return true;
  }
  return value.toString().length() <= max;
}
