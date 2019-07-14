/** 
 * Skips HTML comments.
 */
@Override public void comment(final CharSequence comment){
  strippedCharsCount+=comment.length() + 7;
}
