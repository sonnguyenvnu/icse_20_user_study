/** 
 * Unsafe version of  {@link #valueString}. 
 */
public static String nvalueString(long struct){
  return memUTF8(memGetAddress(struct + ObjCPropertyAttribute.VALUE));
}
