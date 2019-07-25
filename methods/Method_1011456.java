public void other(SNode node){
  SNodeOperations.isAttribute(node);
  List<SNode> nodes=AttributeOperations.getAttributeList(node,new IAttributeDescriptor.AllAttributes());
  SNode firstNode=ListSequence.fromList(AttributeOperations.getAttributeList(node,new IAttributeDescriptor.AllAttributes())).first();
}
