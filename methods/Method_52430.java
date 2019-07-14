private SimpleTypedNameDeclaration convertToSimpleType(List<ASTClassOrInterfaceType> types){
  SimpleTypedNameDeclaration result=null;
  for (  ASTClassOrInterfaceType t : types) {
    SimpleTypedNameDeclaration type=convertToSimpleType(t);
    if (result == null) {
      result=type;
    }
 else {
      result.addNext(type);
    }
  }
  return result;
}
