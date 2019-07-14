public FieldNode defineField(String name,String desc){
  if (fieldNodes == null) {
    fieldNodes=new ArrayList<>(1);
  }
  for (  FieldNode fieldNode : fieldNodes) {
    if (fieldNode.equals(name,desc)) {
      return fieldNode;
    }
  }
  FieldNode fieldNode=new FieldNode(this,name,desc);
  fieldNodes.add(fieldNode);
  return fieldNode;
}
