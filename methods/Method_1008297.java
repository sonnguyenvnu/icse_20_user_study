/** 
 * Closes the current lineString by adding the starting point as the end point. This will have no effect if starting and end point are already the same.
 */
public LineStringBuilder close(){
  Coordinate start=coordinates.get(0);
  Coordinate end=coordinates.get(coordinates.size() - 1);
  if (start.x != end.x || start.y != end.y) {
    coordinates.add(start);
  }
  return this;
}
