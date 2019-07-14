/** 
 * Appends missing space if the output doesn't end with whitespace.
 */
protected void appendMissingSpace(final StringBuilder out){
  int len=out.length();
  if (len == 0) {
    return;
  }
  len--;
  if (!CharUtil.isWhitespace(out.charAt(len))) {
    out.append(' ');
  }
}
