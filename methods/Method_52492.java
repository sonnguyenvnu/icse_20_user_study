/** 
 * This method looks for method invocations be simple name. It searches outwards class declarations and their supertypes and in the end, static method imports. Compiles a list of potentially applicable methods. https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.1
 */
private List<MethodType> getLocalApplicableMethods(TypeNode node,String methodName,List<JavaTypeDefinition> typeArguments,int argArity,Class<?> accessingClass){
  List<MethodType> foundMethods=new ArrayList<>();
  if (accessingClass == null) {
    return foundMethods;
  }
  for (node=getEnclosingTypeDeclaration(node); node != null; node=getEnclosingTypeDeclaration(node.jjtGetParent())) {
    foundMethods.addAll(getApplicableMethods(node.getTypeDefinition(),methodName,typeArguments,argArity,accessingClass));
  }
  foundMethods.addAll(searchImportedStaticMethods(methodName,typeArguments,argArity,accessingClass));
  return foundMethods;
}
