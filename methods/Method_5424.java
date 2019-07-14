private static int parseIntAttr(String line,Pattern pattern) throws ParserException {
  return Integer.parseInt(parseStringAttr(line,pattern,Collections.emptyMap()));
}
