/** 
 * Increments the counters by the values given by the collection of elements.
 * @param children list of nodes, which counters will be added to this node
 */
public void increment(final Collection<? extends ICoverageNode> children){
  for (  final ICoverageNode child : children) {
    increment(child);
  }
}
