/** 
 * Makes tabs &gt;= 0 and appends <tt>tabs*indentValue</tt> spaces to result.
 */
private void printIndentation(){
  if (tabs <= 0) {
    tabs=0;
    return;
  }
  final int spaces=tabs * indentValue;
  for (int i=0; i < spaces; i++) {
    result.append(' ');
  }
}
