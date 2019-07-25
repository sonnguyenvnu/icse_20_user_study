/** 
 * Create a predicate to check that a type has a given name.
 * @param name name on the type
 * @return Predicate to check that a type has a given name.
 */
public static Predicate<Class<?>> named(final String name){
  return clazz -> clazz.getName().equals(name);
}
