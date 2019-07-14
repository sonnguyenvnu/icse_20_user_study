/** 
 * Load first s.
 * @param < S >   the type parameter
 * @param clazz the clazz
 * @return the s
 */
public static <S>S loadFirst(final Class<S> clazz){
  final ServiceLoader<S> loader=loadAll(clazz);
  final Iterator<S> iterator=loader.iterator();
  if (!iterator.hasNext()) {
    throw new IllegalStateException(String.format("No implementation defined in /META-INF/services/%s, please check whether the file exists and has the right implementation class!",clazz.getName()));
  }
  return iterator.next();
}
