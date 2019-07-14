/** 
 * Returns a new  {@code XSetWindowAttributes} instance for the specified memory address. 
 */
public static XSetWindowAttributes create(long address){
  return wrap(XSetWindowAttributes.class,address);
}
