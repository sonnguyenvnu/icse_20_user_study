/** 
 * Checks whether the container is a subset of this container or not
 * @param subset the container to be tested
 * @return true if the parameter is a subset of this container
 */
public boolean contains(Container subset){
  if (subset instanceof RunContainer) {
    return contains((RunContainer)subset);
  }
 else   if (subset instanceof ArrayContainer) {
    return contains((ArrayContainer)subset);
  }
 else   if (subset instanceof BitmapContainer) {
    return contains((BitmapContainer)subset);
  }
  return false;
}
