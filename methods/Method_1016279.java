/** 
 * Create a predicate to check that a field has a certain name pattern.
 * @param name pattern of the field name to check
 * @return Predicate to check that a field has a certain name pattern
 */
public static Predicate<Field> named(final String name){
  return field -> Pattern.compile(name).matcher(field.getName()).matches();
}
