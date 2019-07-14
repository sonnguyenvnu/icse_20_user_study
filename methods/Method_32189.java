/** 
 * Convert the specified text and locale into a value.
 * @param text  the text to convert
 * @param locale  the locale to convert using
 * @return the value extracted from the text
 * @throws IllegalArgumentException if the text is invalid
 */
protected int convertText(String text,Locale locale){
  try {
    return Integer.parseInt(text);
  }
 catch (  NumberFormatException ex) {
    throw new IllegalFieldValueException(getType(),text);
  }
}
