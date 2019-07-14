/** 
 * Converts an integer to a string, and writes it to the given writer. <p>This method is optimized for converting small values to strings.
 * @param out receives integer converted to a string
 * @param value value to convert to a string
 */
public static void writeUnpaddedInteger(Writer out,int value) throws IOException {
  if (value < 0) {
    out.write('-');
    if (value != Integer.MIN_VALUE) {
      value=-value;
    }
 else {
      out.write("" + -(long)Integer.MIN_VALUE);
      return;
    }
  }
  if (value < 10) {
    out.write(value + '0');
  }
 else   if (value < 100) {
    int d=((value + 1) * 13421772) >> 27;
    out.write(d + '0');
    out.write(value - (d << 3) - (d << 1) + '0');
  }
 else {
    out.write(Integer.toString(value));
  }
}
