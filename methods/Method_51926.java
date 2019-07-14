/** 
 * Gets the next word (characters until next whitespace, punctuation, or anything that is not a letter or digit) at the given position.
 * @param text the complete text
 * @param position the position, at which the word starts
 * @return the word
 * @deprecated This method is deprecated and will be removed with PMD 7.0.0.This method has been intended to parse javadoc tags. A more useful solution will be added around the AST node  {@link FormalComment}, which contains as children  {@link JavadocElement} nodes, which inturn provide access to the  {@link JavadocTag}.
 */
@Deprecated public static String wordAfter(String text,int position){
  if (text == null || position >= text.length()) {
    return null;
  }
  int newposition=position + 1;
  int end=newposition;
  char ch=text.charAt(end);
  while (Character.isLetterOrDigit(ch) && end < text.length()) {
    ch=text.charAt(++end);
  }
  return text.substring(newposition,end);
}
