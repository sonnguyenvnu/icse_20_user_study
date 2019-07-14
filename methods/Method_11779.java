/** 
 * Creates a  {@link Filter} which is only passed by tests that arenot categorized with any of the specified categories.
 * @param categories Category classes.
 */
@Override protected Filter createFilter(List<Class<?>> categories){
  return new ExcludesAny(categories);
}
