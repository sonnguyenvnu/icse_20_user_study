/** 
 * Pump any '\t' and ' ' to buf, handle any following comment, and if the next character is '\n', discard it.
 * @return Whether a '\n' was found and discarded.
 */
private boolean readForNewLine(){
  final int savedTabs=tabs;
  char c=peek();
  while (!EOF && (c == '\t' || c == ' ')) {
    buf.append(nextChar());
    c=peek();
  }
  if (c == '/') {
    buf.append(nextChar());
    c=peek();
    if (c == '*') {
      buf.append(nextChar());
      handleMultiLineComment();
    }
 else     if (c == '/') {
      buf.append(nextChar());
      handleSingleLineComment();
      return true;
    }
  }
  c=peek();
  if (c == '\n') {
    nextChar();
    tabs=savedTabs;
    return true;
  }
  return false;
}
