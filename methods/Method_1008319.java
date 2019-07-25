/** 
 * Copy all coordinate to a new Array
 * @param closed if set to true the first point of the array is repeated as last element
 * @return Array of coordinates
 */
protected Coordinate[] coordinates(boolean closed){
  Coordinate[] result=coordinates.toArray(new Coordinate[coordinates.size() + (closed ? 1 : 0)]);
  if (closed) {
    result[result.length - 1]=result[0];
  }
  return result;
}
