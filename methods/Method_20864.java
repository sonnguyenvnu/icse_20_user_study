public static boolean isValidPassword(final @Nullable String str){
  return !isEmpty(str) && str.length() > 5;
}
