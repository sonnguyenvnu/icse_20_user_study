@Override String kindDisplayName(ASTVariableDeclaratorId node,PropertyDescriptor<Pattern> descriptor){
  ASTFieldDeclaration field=(ASTFieldDeclaration)node.getNthParent(2);
  if (field.isFinal() && field.isStatic()) {
    return field.isPublic() ? "public constant" : "constant";
  }
 else   if (field.isFinal()) {
    return "final field";
  }
 else   if (field.isStatic()) {
    return "static field";
  }
 else {
    return "field";
  }
}
