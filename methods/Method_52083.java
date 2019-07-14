/** 
 * Add a violation, if the node image is one of the loop variables.
 */
private void checkVariable(Object data,Set<String> loopVariables,AbstractNode node){
  if (node != null && loopVariables.contains(node.getImage())) {
    addViolation(data,node,node.getImage());
  }
}
