protected static ASTNode findClosestParentNode(int lineNumber,ASTNode node){
  for (  StructuralPropertyDescriptor prop : (Iterable<StructuralPropertyDescriptor>)node.structuralPropertiesForType()) {
    if (prop.isChildProperty() || prop.isSimpleProperty()) {
      if (node.getStructuralProperty(prop) != null) {
        if (node.getStructuralProperty(prop) instanceof ASTNode) {
          ASTNode cnode=(ASTNode)node.getStructuralProperty(prop);
          int cLineNum=((CompilationUnit)cnode.getRoot()).getLineNumber(cnode.getStartPosition() + cnode.getLength());
          if (getLineNumber(cnode) <= lineNumber && lineNumber <= cLineNum) {
            return findClosestParentNode(lineNumber,cnode);
          }
        }
      }
    }
 else     if (prop.isChildListProperty()) {
      List<ASTNode> nodelist=(List<ASTNode>)node.getStructuralProperty(prop);
      for (      ASTNode cnode : nodelist) {
        int cLineNum=((CompilationUnit)cnode.getRoot()).getLineNumber(cnode.getStartPosition() + cnode.getLength());
        if (getLineNumber(cnode) <= lineNumber && lineNumber <= cLineNum) {
          return findClosestParentNode(lineNumber,cnode);
        }
      }
    }
  }
  return node;
}
