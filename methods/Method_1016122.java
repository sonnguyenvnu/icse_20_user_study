/** 
 * Returns middle point for the specified rectangle.
 * @param rectangle rectangle to process
 * @return middle point for the specified rectangle
 */
public static Point middle(final Rectangle rectangle){
  return new Point(rectangle.x + rectangle.width / 2,rectangle.y + rectangle.height / 2);
}
