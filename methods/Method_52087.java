protected boolean isIPv4(final char firstChar,final String s){
  if (s.length() < 7 || !isLatinDigit(firstChar) || s.indexOf('.') < 0) {
    return false;
  }
  Matcher matcher=IPV4_PATTERN.matcher(s);
  if (matcher.matches()) {
    for (int i=1; i <= matcher.groupCount(); i++) {
      int octet=Integer.parseInt(matcher.group(i));
      if (octet < 0 || octet > 255) {
        return false;
      }
    }
    return true;
  }
 else {
    return false;
  }
}
