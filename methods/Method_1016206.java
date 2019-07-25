/** 
 * Returns cloned node state.
 * @return cloned node state
 */
@Override protected NodeState clone(){
  return new NodeState(expanded,selected);
}
