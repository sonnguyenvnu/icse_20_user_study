public static boolean validate(final Object value,final int min,final int max){
  if (value == null) {
    return true;
  }
  final int len=value.toString().length();
  return len >= min && len <= max;
}
