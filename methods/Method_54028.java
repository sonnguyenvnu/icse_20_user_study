/** 
 * Returns a new  {@code AIMeshMorphAnim} instance for the specified memory address. 
 */
public static AIMeshMorphAnim create(long address){
  return wrap(AIMeshMorphAnim.class,address);
}
