/** 
 * Checks whether or not this circle contains a given point.
 * @param point The {@link Vector2} that contains the point coordinates.
 * @return true if this circle contains this point; false otherwise. 
 */
public boolean contains(Vector2 point){
  float dx=x - point.x;
  float dy=y - point.y;
  return dx * dx + dy * dy <= radius * radius;
}
