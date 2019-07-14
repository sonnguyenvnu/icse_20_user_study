/** 
 * Creates a new instance of this class.
 * @param clazz The class to instantiate.
 * @param < T >   The type of the new instance.
 * @return The new instance.
 * @throws FlywayException Thrown when the instantiation failed.
 */
public static synchronized <T>T instantiate(Class<T> clazz){
  try {
    return clazz.getDeclaredConstructor().newInstance();
  }
 catch (  Exception e) {
    throw new FlywayException("Unable to instantiate class " + clazz.getName() + " : " + e.getMessage(),e);
  }
}
