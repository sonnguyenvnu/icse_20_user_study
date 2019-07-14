/** 
 * Writes a trace message to indicate that a given section of code has begun. This call must be followed by a corresponding call to  {@link #endSection()} on the same thread.
 */
public static void beginSection(String name){
  sInstance.beginSection(name);
}
