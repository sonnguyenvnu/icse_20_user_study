public ClassNode defineClass(String className){
  checkClassName(className);
  int index=Collections.binarySearch(classNodes,className,ClassNodeComparator.INSTANCE);
  ClassNode classNode;
  if (index >= 0) {
    classNode=classNodes.get(index);
  }
 else {
    classNode=new ClassNode(className);
    classNodes.add(-(index + 1),classNode);
  }
  return classNode;
}
