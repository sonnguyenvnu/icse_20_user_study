/** 
 * Returns minimum dimension combined from specified ones.
 * @param dimension1 first dimension
 * @param dimension2 second dimension
 * @return minimum dimension
 */
public static Dimension min(final Dimension dimension1,final Dimension dimension2){
  if (dimension1 == null || dimension2 == null) {
    return null;
  }
 else {
    return new Dimension(Math.min(dimension1.width,dimension2.width),Math.min(dimension1.height,dimension2.height));
  }
}
