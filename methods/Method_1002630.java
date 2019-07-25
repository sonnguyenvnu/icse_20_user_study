/** 
 * Record a message signifying an warning condition.
 * @param msg signifying an warning.
 */
public void warning(final String msg){
  warnings++;
  if (!suppressOutput) {
    out.println("WARNING: " + msg);
  }
  if (warningsFatal && stopOnError) {
    throw new IllegalArgumentException(msg);
  }
}
