/** 
 * @return Whether it's necessary to generate a new changeSet for the sub-tree with root in next
 * @param previous the {@link Section} with the same globalScope in the previous tree.
 * @param next the {@link Section} for which we want to check whether a new changeSet is needed.
 */
final boolean shouldComponentUpdate(Section previous,Section next){
  boolean dirty=false;
  if (!dirty && next != null) {
    dirty|=next.isInvalidated();
  }
  return dirty || shouldUpdate(previous,next);
}
