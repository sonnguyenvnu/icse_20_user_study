/** 
 * Return a hex String form of an object's identity hash code.
 * @param obj the object
 * @return the object's identity code in hex notation
 */
public static String getIdentityHexString(Object obj){
  return Integer.toHexString(System.identityHashCode(obj));
}
