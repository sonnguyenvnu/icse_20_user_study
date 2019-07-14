/** 
 * Provide a list of types of the parameters of the given method declaration. The types are simple type images.
 * @param mnd the method declaration.
 * @return List of types
 */
private List<TypedNameDeclaration> determineParameterTypes(MethodNameDeclaration mnd){
  List<ASTFormalParameter> parameters=mnd.getMethodNameDeclaratorNode().findDescendantsOfType(ASTFormalParameter.class);
  if (parameters.isEmpty()) {
    return Collections.emptyList();
  }
  List<TypedNameDeclaration> parameterTypes=new ArrayList<>(parameters.size());
  SourceFileScope fileScope=getEnclosingScope(SourceFileScope.class);
  Map<String,Node> qualifiedTypeNames=fileScope.getQualifiedTypeNames();
  for (  ASTFormalParameter p : parameters) {
    if (p.isExplicitReceiverParameter()) {
      continue;
    }
    String typeImage=p.getTypeNode().getTypeImage();
    typeImage=qualifyTypeName(typeImage);
    Node declaringNode=qualifiedTypeNames.get(typeImage);
    Class<?> resolvedType=fileScope.resolveType(typeImage);
    if (resolvedType == null) {
      resolvedType=resolveGenericType(p,typeImage);
    }
    parameterTypes.add(new SimpleTypedNameDeclaration(typeImage,resolvedType,determineSuper(declaringNode)));
  }
  return parameterTypes;
}
