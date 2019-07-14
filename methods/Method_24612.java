/** 
 * Add multiple  {@link VariableNode}s as children.
 * @param children the list of {@link VariableNode}s to add.
 */
public void addChildren(List<VariableNode> children){
  for (  VariableNode child : children) {
    addChild(child);
  }
}
