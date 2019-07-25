/** 
 * Returns component size represented as a rectangle with zero X and Y coordinates.
 * @param component component to process
 * @return component size rectangle
 */
public static Rectangle size(final Component component){
  return new Rectangle(0,0,component.getWidth(),component.getHeight());
}
