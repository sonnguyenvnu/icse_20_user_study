/** 
 * Swallows all characters in this stream until any of these delimiting characters has been encountered.
 * @param delimiter1 The first delimiting character.
 * @param delimiter2 The second delimiting character.
 */
public void swallowUntilExcluding(char delimiter1,char delimiter2) throws IOException {
  do {
    if (peek(delimiter1,delimiter2)) {
      break;
    }
    int r=read();
    if (r == -1) {
      break;
    }
  }
 while (true);
}
