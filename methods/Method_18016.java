/** 
 * Adds a connection between two nodes to this graph. The connection will not be actualized until {@link #activate} is called, and will be removed once {@link #deactivate} is called.
 */
public void addBinding(ValueNode fromNode,ValueNode toNode,String name){
  if (mHasBeenActivated) {
    throw new RuntimeException("Trying to add binding after DataFlowGraph has already been activated.");
  }
  mBindings.addBinding(fromNode,toNode,name);
  mAllNodes.add(fromNode);
  mAllNodes.add(toNode);
}
