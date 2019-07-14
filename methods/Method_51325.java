/** 
 * Returns a string representation of the default value.
 * @return A string representation of the default value.
 */
@Override protected String defaultAsString(){
  return asDelimitedString(defaultValue(),multiValueDelimiter());
}
