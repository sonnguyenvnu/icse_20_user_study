private void collectNames(String target,List<String> names,Node node){
  for (int i=0; i < node.jjtGetNumChildren(); i++) {
    Node child=node.jjtGetChild(i);
    if (child.jjtGetNumChildren() > 0) {
      collectNames(target,names,child);
    }
 else {
      if (child instanceof ASTName && isQualifiedName(child) && target.equals(getVariableName(child.getImage()))) {
        names.add(child.getImage());
      }
    }
  }
}
