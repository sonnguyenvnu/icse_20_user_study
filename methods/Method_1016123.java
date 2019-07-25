/** 
 * Returns middle point between the specified points.
 * @param p1 first point
 * @param p2 second point
 * @return middle point between the specified points
 */
public static Point middle(final Point p1,final Point p2){
  return new Point((p1.x + p2.x) / 2,(p1.y + p2.y) / 2);
}
