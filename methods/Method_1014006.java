@Override public String format(@Nullable String pattern){
  if (pattern == null) {
    return DateTimeFormatter.ofPattern(DATE_PATTERN).format(zonedDateTime);
  }
  return String.format(pattern,zonedDateTime);
}
