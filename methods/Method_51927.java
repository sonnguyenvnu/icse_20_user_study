/** 
 * Gets the remaining line after a specific position.
 * @param text the complete text
 * @param position the position from which the comment should be returned
 * @return the part of the text
 * @deprecated This method is deprecated and will be removed with PMD 7.0.0.This method has been intended to parse javadoc tags. A more useful solution will be added around the AST node  {@link FormalComment}, which contains as children  {@link JavadocElement} nodes, which inturn provide access to the  {@link JavadocTag}.
 */
@Deprecated public static String javadocContentAfter(String text,int position){
  if (text == null || position > text.length()) {
    return null;
  }
  int endPos=text.indexOf('\n',position);
  if (endPos < 0) {
    endPos=text.length();
  }
  if (StringUtils.isNotBlank(text.substring(position,endPos))) {
    return text.substring(position,endPos).trim();
  }
  if (text.indexOf('@',endPos) >= 0) {
    return null;
  }
  int nextEndPos=text.indexOf('\n',endPos + 1);
  if (StringUtils.isNotBlank(text.substring(endPos,nextEndPos))) {
    return text.substring(endPos,nextEndPos).trim();
  }
  return null;
}
