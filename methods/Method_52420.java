@Override public String toString(){
  if (node instanceof ASTClassOrInterfaceDeclaration) {
    if (((ASTClassOrInterfaceDeclaration)node).isInterface()) {
      return "Interface " + node.getImage();
    }
 else {
      return "Class " + node.getImage();
    }
  }
 else {
    return "Enum " + node.getImage();
  }
}
