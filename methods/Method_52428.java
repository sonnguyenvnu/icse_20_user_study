/** 
 * Provide a list of types of the arguments of the given method call. The types are simple type images. If the argument type cannot be determined (e.g. because it is itself the result of a method call), the parameter type is used - so it is assumed, it is of the correct type. This might cause confusion when methods are overloaded.
 * @param occurrence the method call
 * @param parameterTypes the parameter types of the called method
 * @return the list of argument types
 */
private List<TypedNameDeclaration> determineArgumentTypes(JavaNameOccurrence occurrence,List<TypedNameDeclaration> parameterTypes){
  ASTArgumentList arguments=null;
  Node nextSibling;
  if (occurrence.getLocation() instanceof ASTPrimarySuffix) {
    nextSibling=getNextSibling(occurrence.getLocation());
  }
 else {
    nextSibling=getNextSibling(occurrence.getLocation().jjtGetParent());
  }
  if (nextSibling != null) {
    arguments=nextSibling.getFirstDescendantOfType(ASTArgumentList.class);
  }
  if (arguments == null) {
    return Collections.emptyList();
  }
  List<TypedNameDeclaration> argumentTypes=new ArrayList<>(arguments.jjtGetNumChildren());
  Map<String,Node> qualifiedTypeNames=getEnclosingScope(SourceFileScope.class).getQualifiedTypeNames();
  for (int i=0; i < arguments.jjtGetNumChildren(); i++) {
    Node argument=arguments.jjtGetChild(i);
    Node child=null;
    boolean isMethodCall=false;
    if (argument.jjtGetNumChildren() > 0 && argument.jjtGetChild(0).jjtGetNumChildren() > 0 && argument.jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() > 0) {
      child=argument.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
      isMethodCall=argument.jjtGetChild(0).jjtGetNumChildren() > 1;
    }
    TypedNameDeclaration type=null;
    if (child instanceof ASTName && !isMethodCall) {
      ASTName name=(ASTName)child;
      Scope s=name.getScope();
      final JavaNameOccurrence nameOccurrence=new JavaNameOccurrence(name,name.getImage());
      while (s != null) {
        if (s.contains(nameOccurrence)) {
          break;
        }
        s=s.getParent();
      }
      if (s != null) {
        Map<VariableNameDeclaration,List<NameOccurrence>> vars=s.getDeclarations(VariableNameDeclaration.class);
        for (        VariableNameDeclaration d : vars.keySet()) {
          if (d.getImage().equals(name.getImage()) && d.getTypeImage() != null) {
            String typeName=d.getTypeImage();
            typeName=qualifyTypeName(typeName);
            Node declaringNode=qualifiedTypeNames.get(typeName);
            type=new SimpleTypedNameDeclaration(typeName,this.getEnclosingScope(SourceFileScope.class).resolveType(typeName),determineSuper(declaringNode));
            break;
          }
        }
      }
    }
 else     if (child instanceof ASTLiteral) {
      ASTLiteral literal=(ASTLiteral)child;
      if (literal.isCharLiteral()) {
        type=new SimpleTypedNameDeclaration("char",literal.getType());
      }
 else       if (literal.isStringLiteral()) {
        type=new SimpleTypedNameDeclaration("String",literal.getType());
      }
 else       if (literal.isFloatLiteral()) {
        type=new SimpleTypedNameDeclaration("float",literal.getType());
      }
 else       if (literal.isDoubleLiteral()) {
        type=new SimpleTypedNameDeclaration("double",literal.getType());
      }
 else       if (literal.isIntLiteral()) {
        type=new SimpleTypedNameDeclaration("int",literal.getType());
      }
 else       if (literal.isLongLiteral()) {
        type=new SimpleTypedNameDeclaration("long",literal.getType());
      }
 else       if (literal.jjtGetNumChildren() == 1 && literal.jjtGetChild(0) instanceof ASTBooleanLiteral) {
        type=new SimpleTypedNameDeclaration("boolean",Boolean.TYPE);
      }
    }
 else     if (child instanceof ASTAllocationExpression && child.jjtGetChild(0) instanceof ASTClassOrInterfaceType) {
      ASTClassOrInterfaceType classInterface=(ASTClassOrInterfaceType)child.jjtGetChild(0);
      type=convertToSimpleType(classInterface);
    }
    if (type == null && !parameterTypes.isEmpty()) {
      if (parameterTypes.size() > i) {
        type=parameterTypes.get(i);
      }
 else {
        type=parameterTypes.get(parameterTypes.size() - 1);
      }
    }
    if (type != null && type.getType() == null) {
      Class<?> typeBound=resolveGenericType(argument,type.getTypeImage());
      if (typeBound != null) {
        type=new SimpleTypedNameDeclaration(type.getTypeImage(),typeBound);
      }
    }
    argumentTypes.add(type);
  }
  return argumentTypes;
}
