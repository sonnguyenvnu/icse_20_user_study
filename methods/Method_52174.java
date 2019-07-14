private void checkField(ASTType typeNode,ASTVariableDeclarator node,Object data){
  for (  String prefix : getProperty(BOOLEAN_FIELD_PREFIXES_PROPERTY)) {
    if (hasPrefix(node.getName(),prefix) && !isBooleanType(typeNode)) {
      addViolationWithMessage(data,node,"Linguistics Antipattern - The field ''{0}'' indicates linguistically it is a boolean, but it is ''{1}''",new Object[]{node.getName(),typeNode.getTypeImage()});
    }
  }
}
