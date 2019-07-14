/** 
 * Takes all whitespace off the end of its argument.
 */
static private void trimRight(final StringBuilder sb){
  while (sb.length() >= 1 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
    sb.setLength(sb.length() - 1);
  }
}
