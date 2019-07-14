/** 
 * Returns the location of a given static field.
 * @param clazz the class containing the field
 * @param fieldName the name of the field
 * @return the address offset of the field
 */
public static long objectFieldOffset(Class<?> clazz,String fieldName){
  try {
    return UNSAFE.objectFieldOffset(clazz.getDeclaredField(fieldName));
  }
 catch (  NoSuchFieldException|SecurityException e) {
    throw new Error(e);
  }
}
