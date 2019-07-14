/** 
 * Converts an integer to a string, and writes it to the given writer. <p>This method is optimized for converting small values to strings.
 * @param out receives integer converted to a string
 * @param value value to convert to a string
 */
public static void writeUnpaddedInteger(Writer out,long value) throws IOException {
  int intValue=(int)value;
  if (intValue == value) {
    writeUnpaddedInteger(out,intValue);
  }
 else {
    out.write(Long.toString(value));
  }
}
