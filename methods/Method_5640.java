private static void parsePositionAttribute(String s,WebvttCue.Builder builder) throws NumberFormatException {
  int commaIndex=s.indexOf(',');
  if (commaIndex != -1) {
    builder.setPositionAnchor(parsePositionAnchor(s.substring(commaIndex + 1)));
    s=s.substring(0,commaIndex);
  }
 else {
    builder.setPositionAnchor(Cue.TYPE_UNSET);
  }
  builder.setPosition(WebvttParserUtil.parsePercentage(s));
}
