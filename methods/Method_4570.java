/** 
 * Populates the holder with data parsed from a gapless playback comment (stored in an ID3 header or MPEG 4 user data), if valid and non-zero.
 * @param data The comment's payload data.
 * @return Whether the holder was populated.
 */
private boolean setFromComment(String data){
  Matcher matcher=GAPLESS_COMMENT_PATTERN.matcher(data);
  if (matcher.find()) {
    try {
      int encoderDelay=Integer.parseInt(matcher.group(1),16);
      int encoderPadding=Integer.parseInt(matcher.group(2),16);
      if (encoderDelay > 0 || encoderPadding > 0) {
        this.encoderDelay=encoderDelay;
        this.encoderPadding=encoderPadding;
        return true;
      }
    }
 catch (    NumberFormatException e) {
    }
  }
  return false;
}
