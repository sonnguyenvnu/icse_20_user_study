/** 
 * Converts an integer to a string, and appends it to the given appendable. <p>This method is optimized for converting small values to strings.
 * @param appendable receives integer converted to a string
 * @param value value to convert to a string
 * @since 2.4
 */
public static void appendUnpaddedInteger(Appendable appendable,int value) throws IOException {
  if (value < 0) {
    appendable.append('-');
    if (value != Integer.MIN_VALUE) {
      value=-value;
    }
 else {
      appendable.append("" + -(long)Integer.MIN_VALUE);
      return;
    }
  }
  if (value < 10) {
    appendable.append((char)(value + '0'));
  }
 else   if (value < 100) {
    int d=((value + 1) * 13421772) >> 27;
    appendable.append((char)(d + '0'));
    appendable.append((char)(value - (d << 3) - (d << 1) + '0'));
  }
 else {
    appendable.append(Integer.toString(value));
  }
}
