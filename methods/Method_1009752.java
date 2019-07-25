/** 
 * @return The integer value of the given key's text.
 */
static int digit(String text){
  for (int i=0; i < NUMBERS_TEXTS.length; i++) {
    if (NUMBERS_TEXTS[i].equals(text)) {
      return i;
    }
  }
  throw new IllegalArgumentException("Cannot convert \"" + text + "\" to digit");
}
