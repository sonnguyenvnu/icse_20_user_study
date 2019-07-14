/** 
 * Gets the current time in milliseconds. <p> By default this returns <code>System.currentTimeMillis()</code>. This may be changed using other methods in this class.
 * @return the current time in milliseconds from 1970-01-01T00:00:00Z
 */
public static final long currentTimeMillis(){
  return cMillisProvider.getMillis();
}
