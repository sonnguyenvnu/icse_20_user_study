/** 
 * Checks whether this literal is a long integer.
 * @return <code>true</code> if this literal is a long
 */
public boolean isLongLiteral(){
  String image=getImage();
  if (isInt && image != null && image.length() > 0) {
    if (image.endsWith("l") || image.endsWith("L")) {
      return true;
    }
  }
  return false;
}
