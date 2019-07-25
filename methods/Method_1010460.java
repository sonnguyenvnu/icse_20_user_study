public static int hash(SNode node){
  int result=node.getConcept().hashCode();
  for (  SReference reference : node.getReferences()) {
    SNode targetNode=jetbrains.mps.util.SNodeOperations.getTargetNodeSilently(reference);
    if (targetNode != null) {
      result=31 * result + reference.getLink().hashCode();
      result=31 * result + targetNode.hashCode();
    }
  }
  Map<String,String> properties=jetbrains.mps.util.SNodeOperations.getProperties(node);
  for (  String propertyName : properties.keySet()) {
    result=31 * result + propertyName.hashCode();
  }
  for (  String propertyValue : properties.values()) {
    result=31 * result + propertyValue.hashCode();
  }
  for (  SNode child : node.getChildren()) {
    if (AttributeOperations.isAttribute(child))     continue;
    result=31 * result + child.getContainmentLink().hashCode();
    result=31 * result + hash(child);
  }
  return result;
}
