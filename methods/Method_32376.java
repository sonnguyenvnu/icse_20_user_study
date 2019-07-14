/** 
 * Converts an integer to a string, and appends it to the given buffer. <p>This method is optimized for converting small values to strings.
 * @param buf receives integer converted to a string
 * @param value value to convert to a string
 */
public static void appendUnpaddedInteger(StringBuffer buf,long value){
  try {
    appendUnpaddedInteger((Appendable)buf,value);
  }
 catch (  IOException e) {
  }
}
