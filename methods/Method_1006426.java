/** 
 * Checks whether the container is a subset of this container or not
 * @param subset the container to be tested
 * @return true if the parameter is a subset of this container
 */
public boolean contains(MappeableContainer subset){
  if (subset instanceof MappeableRunContainer) {
    return contains((MappeableRunContainer)subset);
  }
 else   if (subset instanceof MappeableArrayContainer) {
    return contains((MappeableArrayContainer)subset);
  }
 else   if (subset instanceof MappeableBitmapContainer) {
    return contains((MappeableBitmapContainer)subset);
  }
  return false;
}
