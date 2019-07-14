/** 
 * Get the human-readable, text value of this field from the field value. If the specified locale is null, the default locale is used. <p> The default implementation returns Integer.toString(get(instant)). <p> Note: subclasses that override this method should also override getMaximumTextLength.
 * @param fieldValue  the numeric value to convert to text
 * @param locale the locale to use for selecting a text symbol, null for default
 * @return the text value of the field
 */
public String getAsText(int fieldValue,Locale locale){
  return Integer.toString(fieldValue);
}
