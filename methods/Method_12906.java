/** 
 * Determine whether the given Collection only contains a single unique object.
 * @param collection the Collection to check
 * @return {@code true} if the collection contains a single reference ormultiple references to the same instance,  {@code false} else
 */
public static boolean hasUniqueObject(Collection<?> collection){
  if (isEmpty(collection)) {
    return false;
  }
  boolean hasCandidate=false;
  Object candidate=null;
  for (  Object elem : collection) {
    if (!hasCandidate) {
      hasCandidate=true;
      candidate=elem;
    }
 else     if (candidate != elem) {
      return false;
    }
  }
  return true;
}
