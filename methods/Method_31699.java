/** 
 * Creates a new instance of this class.
 * @param className   The fully qualified name of the class to instantiate.
 * @param classLoader The ClassLoader to use.
 * @param < T >         The type of the new instance.
 * @return The new instance.
 * @throws FlywayException Thrown when the instantiation failed.
 */
@SuppressWarnings({"unchecked"}) public static synchronized <T>T instantiate(String className,ClassLoader classLoader){
  try {
    return (T)Class.forName(className,true,classLoader).getDeclaredConstructor().newInstance();
  }
 catch (  Exception e) {
    throw new FlywayException("Unable to instantiate class " + className + " : " + e.getMessage(),e);
  }
}
