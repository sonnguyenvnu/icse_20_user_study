public static Date parse(String dateStr) throws ParseException {
  return getSDF(DEFAULT_PATTERN).parse(dateStr);
}
