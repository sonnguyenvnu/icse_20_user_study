/** 
 * Checks whether this literal describes a double.
 * @return <code>true</code> if this literal is a double.
 */
public boolean isDoubleLiteral(){
  String image=getImage();
  if (isFloat && image != null && image.length() > 0) {
    char lastChar=image.charAt(image.length() - 1);
    if (lastChar == 'd' || lastChar == 'D' || Character.isDigit(lastChar) || lastChar == '.') {
      return true;
    }
  }
  return false;
}
