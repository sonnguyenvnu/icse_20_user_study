/** 
 * Returns minimum dimension combined from specified components dimensions.
 * @param component1 first component
 * @param component2 second component
 * @return minimum dimension
 */
public static Dimension min(final Component component1,final Component component2){
  return min(component1.getPreferredSize(),component2.getPreferredSize());
}
