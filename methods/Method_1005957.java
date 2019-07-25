private static String quote(char separatorChar){
  if (StringUtils.contains(PATTERN_CHARS_TO_ESCAPE,separatorChar)) {
    return "\\" + separatorChar;
  }
 else {
    return String.valueOf(separatorChar);
  }
}
