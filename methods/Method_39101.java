/** 
 * Finds existing chunk or creates a new one if does not exist.
 */
public RouteChunk findOrCreateChild(final String value){
  if (children != null) {
    for (    RouteChunk child : children) {
      if (child.get().equals(value)) {
        return child;
      }
    }
  }
  return add(value);
}
