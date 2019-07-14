private String sanitizeBrokenByteToken(Token tok){
  String st=tok.toString();
  if (!(tok instanceof ByteOrderedPartitioner.BytesToken))   return st;
  if (st.startsWith("T")) {
    Matcher m=BROKEN_BYTE_TOKEN_PATTERN.matcher(st);
    if (!m.matches()) {
      logger.warn("Unknown token string format: \"{}\"",st);
    }
 else {
      String old=st;
      st=m.group(1);
      logger.debug("Rewrote token string: \"{}\" -> \"{}\"",old,st);
    }
  }
  return st;
}
