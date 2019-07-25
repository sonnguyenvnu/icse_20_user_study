/** 
 * Count the number of input annotations present in the set.
 * @param classes annotations to count
 * @return number of annotations out of the input array present in the set
 */
@SafeVarargs public final int count(final Class<? extends Annotation>... classes){
  int result=0;
  for (  Class<? extends Annotation> clazz : classes) {
    if (_map.containsKey(clazz)) {
      ++result;
    }
  }
  return result;
}
