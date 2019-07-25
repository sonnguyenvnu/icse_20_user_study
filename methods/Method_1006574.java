public void visit(ASTNode[] nodes,SourceUnit source){
  init(nodes,source);
  AnnotatedNode parent=(AnnotatedNode)nodes[1];
  AnnotationNode node=(AnnotationNode)nodes[0];
  if (!MY_TYPE.equals(node.getClassNode()))   return;
  if (parent instanceof DeclarationExpression) {
    changeBaseScriptTypeFromDeclaration(source,(DeclarationExpression)parent,node);
  }
 else   if (parent instanceof ImportNode || parent instanceof PackageNode) {
    changeBaseScriptTypeFromPackageOrImport(source,parent,node);
  }
 else   if (parent instanceof ClassNode) {
    changeBaseScriptTypeFromClass(source,(ClassNode)parent,node);
  }
}
