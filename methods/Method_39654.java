/** 
 * Returns the  {@link Type} corresponding to the given internal name.
 * @param internalName an internal name.
 * @return the {@link Type} corresponding to the given internal name.
 */
public static Type getObjectType(final String internalName){
  return new Type(internalName.charAt(0) == '[' ? ARRAY : INTERNAL,internalName,0,internalName.length());
}
