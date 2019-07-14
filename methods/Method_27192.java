public static boolean isEmpty(@Nullable String text){
  return text == null || TextUtils.isEmpty(text) || isWhiteSpaces(text) || text.equalsIgnoreCase("null");
}
