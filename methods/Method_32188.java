/** 
 * Sets a value in the milliseconds supplied from a human-readable, text value. If the specified locale is null, the default locale is used. <p> This implementation uses <code>convertText(String, Locale)</code> and {@link #set(ReadablePartial,int,int[],int)}.
 * @param instant  the partial instant
 * @param fieldIndex  the index of this field in the instant
 * @param values  the values of the partial instant which should be updated
 * @param text  the text value to set
 * @param locale the locale to use for selecting a text symbol, null for default
 * @return the passed in values
 * @throws IllegalArgumentException if the text value is invalid
 */
public int[] set(ReadablePartial instant,int fieldIndex,int[] values,String text,Locale locale){
  int value=convertText(text,locale);
  return set(instant,fieldIndex,values,value);
}
