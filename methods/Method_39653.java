/** 
 * Returns the method  {@link Type} corresponding to the given constructor.
 * @param constructor a {@link Constructor} object.
 * @return the method {@link Type} corresponding to the given constructor.
 */
public static Type getType(final Constructor<?> constructor){
  return getType(getConstructorDescriptor(constructor));
}
