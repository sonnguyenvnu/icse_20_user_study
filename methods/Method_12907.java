/** 
 * Find the common element type of the given Collection, if any.
 * @param collection the Collection to check
 * @return the common element type, or {@code null} if no clearcommon type has been found (or the collection was empty)
 */
public static Class<?> findCommonElementType(Collection<?> collection){
  if (isEmpty(collection)) {
    return null;
  }
  Class<?> candidate=null;
  for (  Object val : collection) {
    if (val != null) {
      if (candidate == null) {
        candidate=val.getClass();
      }
 else       if (candidate != val.getClass()) {
        return null;
      }
    }
  }
  return candidate;
}
