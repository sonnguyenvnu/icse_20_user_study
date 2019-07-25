/** 
 * The points of the polyline. This method returns the polyline for convenient method chaining.
 */
public Polyline points(final IGeoPoint... aPoints){
  return points(Arrays.asList(aPoints));
}
