public ConstructorNode defineConstructor(String name,String desc){
  if (constructorNodes == null) {
    constructorNodes=new ArrayList<>(1);
  }
  for (  ConstructorNode constructorNode : constructorNodes) {
    if (constructorNode.equals(name,desc)) {
      return constructorNode;
    }
  }
  ConstructorNode constructorNode=new ConstructorNode(this,name,desc);
  constructorNodes.add(constructorNode);
  return constructorNode;
}
