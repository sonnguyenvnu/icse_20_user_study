/** 
 * Returns the character at the specified number of characters beyond the current position, or a null character if the specified position is at the end of the document.
 * @param ahead The number of characters beyond the current position.
 * @return The character at the current position.
 */
public char peek(int ahead){
  int pos=position_ + ahead;
  if (pos < text_.length()) {
    return text_.charAt(pos);
  }
  return 0;
}
