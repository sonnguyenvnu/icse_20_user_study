/** 
 * Returns middle point between the specified points.
 * @param x1 first point X coordinate
 * @param y1 first point Y coordinate
 * @param x2 second point X coordinate
 * @param y2 second point Y coordinate
 * @return middle point between the specified points
 */
public static Point middle(final int x1,final int y1,final int x2,final int y2){
  return new Point((x1 + x2) / 2,(y1 + y2) / 2);
}
