private String cleanupPersonalString(String text){
  if (text == null) {
    return null;
  }
  text=text.trim();
  final Matcher m=_QUOTED_STRING_WO_CFWS_PATTERN.matcher(text);
  if (!m.matches()) {
    return text;
  }
  text=removeAnyBounding('"','"',m.group());
  text=ESCAPED_BSLASH_PATTERN.matcher(text).replaceAll("\\\\");
  text=ESCAPED_QUOTE_PATTERN.matcher(text).replaceAll("\"");
  return text.trim();
}
