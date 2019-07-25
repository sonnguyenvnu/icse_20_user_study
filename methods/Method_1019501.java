/** 
 * Union the given sketch. <p>This method can be repeatedly called.</p>
 * @param sketchIn The sketch to be merged
 */
public void update(final VarOptItemsSketch<T> sketchIn){
  if (sketchIn != null) {
    mergeInto(sketchIn);
  }
}
