private void setClassNameFrom(JavaNode node){
  String qualifiedName=null;
  for (  AbstractAnyTypeDeclaration parent : node.getParentsOfType(AbstractAnyTypeDeclaration.class)) {
    String clsName=parent.getScope().getEnclosingScope(ClassScope.class).getClassName();
    if (qualifiedName == null) {
      qualifiedName=clsName;
    }
 else {
      qualifiedName=clsName + '$' + qualifiedName;
    }
  }
  if (qualifiedName == null) {
    Set<ClassNameDeclaration> classes=node.getScope().getEnclosingScope(SourceFileScope.class).getClassDeclarations().keySet();
    for (    ClassNameDeclaration c : classes) {
      if (c.getAccessNodeParent() instanceof AccessNode) {
        if (((AccessNode)c.getAccessNodeParent()).isPublic()) {
          qualifiedName=c.getImage();
          break;
        }
      }
    }
    if (qualifiedName == null) {
      for (      ClassNameDeclaration c : classes) {
        if (c.getAccessNodeParent() instanceof AccessNode) {
          if (((AccessNode)c.getAccessNodeParent()).isPackagePrivate()) {
            qualifiedName=c.getImage();
            break;
          }
        }
      }
    }
  }
  if (qualifiedName != null) {
    className=qualifiedName;
  }
}
