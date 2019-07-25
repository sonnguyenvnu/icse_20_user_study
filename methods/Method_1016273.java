/** 
 * Returns maximum dimension combined from specified components dimensions.
 * @param component1 first component
 * @param component2 second component
 * @return maximum dimension
 */
public static Dimension max(final Component component1,final Component component2){
  return max(component1.getPreferredSize(),component2.getPreferredSize());
}
