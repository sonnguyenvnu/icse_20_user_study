protected boolean isAllowedClass(ASTImportDeclaration node){
  String importedName=node.getImportedName();
  for (  String clazz : getProperty(CLASSES_DESCRIPTOR)) {
    if (importedName.equals(clazz)) {
      return true;
    }
  }
  return false;
}
