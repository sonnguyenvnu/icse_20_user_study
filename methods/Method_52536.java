public static List<JavaTypeDefinition> getMethodExplicitTypeArugments(Node node){
  ASTMemberSelector memberSelector=node.getFirstChildOfType(ASTMemberSelector.class);
  if (memberSelector == null) {
    return Collections.emptyList();
  }
  ASTTypeArguments typeArguments=memberSelector.getFirstChildOfType(ASTTypeArguments.class);
  if (typeArguments == null) {
    return Collections.emptyList();
  }
  List<JavaTypeDefinition> result=new ArrayList<>();
  for (int childIndex=0; childIndex < typeArguments.jjtGetNumChildren(); ++childIndex) {
    result.add(((TypeNode)typeArguments.jjtGetChild(childIndex)).getTypeDefinition());
  }
  return result;
}
