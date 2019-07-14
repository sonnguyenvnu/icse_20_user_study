/** 
 * Gets the value of this property from the instant as a string. <p> This method returns the value converted to a <code>String</code> using <code>Integer.toString</code>. This method does NOT return textual descriptions such as 'Monday' or 'January'. See  {@link #getAsText()} and {@link #getAsShortText()} for those.
 * @return the current value
 * @see DateTimeField#get
 * @since 1.1
 */
public String getAsString(){
  return Integer.toString(get());
}
