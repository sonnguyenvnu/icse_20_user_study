public static String format(String pattern,Date date){
  if (pattern == null) {
    pattern=DEFAULT_PATTERN;
  }
  return getSDF(pattern).format(date);
}
