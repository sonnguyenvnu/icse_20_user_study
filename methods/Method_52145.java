@Override String kindDisplayName(ASTAnyTypeDeclaration node,PropertyDescriptor<Pattern> descriptor){
  return isUtilityClass(node) ? "utility class" : node.getTypeKind().getPrintableName();
}
