/** 
 * Get the maximum text value for this field. The default implementation returns the equivalent of Integer.toString(getMaximumValue()).length().
 * @param locale  the locale to use for selecting a text symbol
 * @return the maximum text length
 */
public int getMaximumTextLength(Locale locale){
  int max=getMaximumValue();
  if (max >= 0) {
    if (max < 10) {
      return 1;
    }
 else     if (max < 100) {
      return 2;
    }
 else     if (max < 1000) {
      return 3;
    }
  }
  return Integer.toString(max).length();
}
