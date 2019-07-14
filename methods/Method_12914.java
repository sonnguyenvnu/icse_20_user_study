/** 
 * Return a String representation of an object's overall identity.
 * @param obj the object (may be {@code null})
 * @return the object's identity as String representation,or an empty String if the object was  {@code null}
 */
public static String identityToString(Object obj){
  if (obj == null) {
    return EMPTY_STRING;
  }
  return obj.getClass().getName() + "@" + getIdentityHexString(obj);
}
