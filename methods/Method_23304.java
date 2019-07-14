/** 
 * Get index for the character.
 * @return index into arrays or -1 if not found
 */
protected int index(char c){
  if (lazy) {
    int index=indexActual(c);
    if (index != -1) {
      return index;
    }
    if (font != null && font.canDisplay(c)) {
      addGlyph(c);
      return indexActual(c);
    }
 else {
      return -1;
    }
  }
 else {
    return indexActual(c);
  }
}
