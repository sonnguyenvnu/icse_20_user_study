public static boolean validate(final Object value,final int min){
  if (value == null) {
    return true;
  }
  return value.toString().length() >= min;
}
