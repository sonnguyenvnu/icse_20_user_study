/** 
 * Returns true if this is a String literal with only one character. Handles octal and escape characters.
 * @return true is this is a String literal with only one character
 */
public boolean isSingleCharacterStringLiteral(){
  if (isString) {
    String image=getImage();
    int length=image.length();
    if (length == 3) {
      return true;
    }
 else     if (image.charAt(1) == '\\') {
      return SINGLE_CHAR_ESCAPE_PATTERN.matcher(image).matches();
    }
  }
  return false;
}
