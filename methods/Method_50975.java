public MethodNode defineMethod(String name,String desc){
  if (methodNodes == null) {
    methodNodes=new ArrayList<>(1);
  }
  for (  MethodNode methodNode : methodNodes) {
    if (methodNode.equals(name,desc)) {
      return methodNode;
    }
  }
  MethodNode methodNode=new MethodNode(this,name,desc);
  methodNodes.add(methodNode);
  return methodNode;
}
