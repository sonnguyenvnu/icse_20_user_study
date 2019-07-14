/** 
 * Given a string, extract the first matched comment token as defined in 2822, trimmed; return null on all errors or non-findings. <p> Note for future improvement: if COMMENT_PATTERN could handle nested comments, then this should be able to as well, but if this method were to be used to find the CFWS personal name (see boolean option) then such a nested comment would probably not be the one you were looking for?
 */
private String getFirstComment(final String text){
  if (text == null) {
    return null;
  }
  final Matcher m=_COMMENT_PATTERN.matcher(text);
  if (!m.find()) {
    return null;
  }
  return m.group().trim();
}
