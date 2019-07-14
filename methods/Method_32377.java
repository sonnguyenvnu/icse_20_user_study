/** 
 * Converts an integer to a string, and appends it to the given appendable. <p>This method is optimized for converting small values to strings.
 * @param appendable receives integer converted to a string
 * @param value value to convert to a string
 */
public static void appendUnpaddedInteger(Appendable appendable,long value) throws IOException {
  int intValue=(int)value;
  if (intValue == value) {
    appendUnpaddedInteger(appendable,intValue);
  }
 else {
    appendable.append(Long.toString(value));
  }
}
