/** 
 * Sets pos to the position of the next character that is not ' ' in chars. If chars[pos] != ' ' already, it will still move on. Then sets EOF if pos has reached the end, or reverses pos by 1 if it has not. <br/> Does nothing if EOF.
 * @param allWsp (boolean) Eat newlines too (all of Character.isWhiteSpace()).
 */
private void advanceToNonSpace(boolean allWsp){
  if (EOF)   return;
  if (allWsp) {
    do {
      pos++;
    }
 while (pos < chars.length && Character.isWhitespace(chars[pos]));
  }
 else {
    do {
      pos++;
    }
 while (pos < chars.length && chars[pos] == ' ');
  }
  if (pos == chars.length - 1) {
    EOF=true;
  }
 else {
    pos--;
  }
}
