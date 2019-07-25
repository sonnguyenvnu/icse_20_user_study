/** 
 * Add a clip indication to the string. It is important that the string length does not exceed the length or the original string.
 * @param text the to be painted
 * @return the clipped string
 */
protected String clip(final String text){
  if (text.length() < 3) {
    return "...";
  }
  return text.substring(0,text.length() - 3) + "...";
}
