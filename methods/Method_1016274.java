/** 
 * Returns maximum dimension combined from specified components dimensions.
 * @param components components
 * @return maximum dimension
 */
public static Dimension max(final Component... components){
  Dimension max=components.length > 0 ? components[0].getPreferredSize() : new Dimension(0,0);
  for (int i=1; i < components.length; i++) {
    max=max(max,components[i].getPreferredSize());
  }
  return max;
}
