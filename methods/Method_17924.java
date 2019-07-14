/** 
 * Called during layout calculation to determine the baseline of a component.
 * @param c The {@link Context} used by this component.
 * @param width The width of this component.
 * @param height The height of this component.
 */
protected int onMeasureBaseline(ComponentContext c,int width,int height){
  return height;
}
