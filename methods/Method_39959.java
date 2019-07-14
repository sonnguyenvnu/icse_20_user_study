public static boolean validate(final Object value,final String substring,final boolean ignoreCase){
  if (value == null) {
    return true;
  }
  if (ignoreCase) {
    return StringUtil.indexOfIgnoreCase(value.toString(),substring) > -1;
  }
  return value.toString().contains(substring);
}
