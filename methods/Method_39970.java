public static boolean validate(final Object value){
  if (value == null) {
    return true;
  }
  return StringUtil.isNotBlank(value.toString());
}
