/** 
 * Converts an integer to a string, prepended with a variable amount of '0' pad characters, and writes it to the given writer. <p>This method is optimized for converting small values to strings.
 * @param out receives integer converted to a string
 * @param value value to convert to a string
 * @param size minimum amount of digits to append
 */
public static void writePaddedInteger(Writer out,int value,int size) throws IOException {
  if (value < 0) {
    out.write('-');
    if (value != Integer.MIN_VALUE) {
      value=-value;
    }
 else {
      for (; size > 10; size--) {
        out.write('0');
      }
      out.write("" + -(long)Integer.MIN_VALUE);
      return;
    }
  }
  if (value < 10) {
    for (; size > 1; size--) {
      out.write('0');
    }
    out.write(value + '0');
  }
 else   if (value < 100) {
    for (; size > 2; size--) {
      out.write('0');
    }
    int d=((value + 1) * 13421772) >> 27;
    out.write(d + '0');
    out.write(value - (d << 3) - (d << 1) + '0');
  }
 else {
    int digits;
    if (value < 1000) {
      digits=3;
    }
 else     if (value < 10000) {
      digits=4;
    }
 else {
      digits=(int)(Math.log(value) / LOG_10) + 1;
    }
    for (; size > digits; size--) {
      out.write('0');
    }
    out.write(Integer.toString(value));
  }
}
