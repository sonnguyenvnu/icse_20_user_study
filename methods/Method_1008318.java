/** 
 * Add a collection of coordinates to the collection
 * @param coordinates array of {@link Coordinate}s to add
 * @return this
 */
public E coordinates(Collection<? extends Coordinate> coordinates){
  this.coordinates.addAll(coordinates);
  return thisRef();
}
