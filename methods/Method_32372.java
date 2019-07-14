/** 
 * Converts an integer to a string, prepended with a variable amount of '0' pad characters, and appends it to the given buffer. <p>This method is optimized for converting small values to strings.
 * @param buf receives integer converted to a string
 * @param value value to convert to a string
 * @param size minimum amount of digits to append
 */
public static void appendPaddedInteger(StringBuffer buf,long value,int size){
  try {
    appendPaddedInteger((Appendable)buf,value,size);
  }
 catch (  IOException e) {
  }
}
