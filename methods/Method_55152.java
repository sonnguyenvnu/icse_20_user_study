/** 
 * Unsafe version of  {@link #typesString}. 
 */
public static String ntypesString(long struct){
  return memUTF8(memGetAddress(struct + ObjCMethodDescription.TYPES));
}
