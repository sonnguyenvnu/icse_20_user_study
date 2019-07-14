/** 
 * Look for methods considering varargs as well. https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.2.4
 */
public static List<MethodType> selectMethodsThirdPhase(List<MethodType> methodsToSearch,ASTArgumentList argList){
  List<MethodType> selectedMethods=new ArrayList<>();
  for (int methodIndex=0; methodIndex < methodsToSearch.size(); ++methodIndex) {
    MethodType methodType=methodsToSearch.get(methodIndex);
    if (!methodType.isParameterized()) {
      throw new ResolutionFailedException();
    }
    if (methodType.isVararg()) {
      boolean methodIsApplicable=true;
      List<JavaTypeDefinition> methodParameters=methodType.getParameterTypes();
      JavaTypeDefinition varargComponentType=methodType.getVarargComponentType();
      if (argList == null) {
        methodIsApplicable=getArity(methodType.getMethod()) == 1;
      }
 else {
        for (int argIndex=0; argIndex < argList.jjtGetNumChildren(); ++argIndex) {
          JavaTypeDefinition parameterType=argIndex < methodParameters.size() - 1 ? methodParameters.get(argIndex) : varargComponentType;
          if (!isMethodConvertible(parameterType,(ASTExpression)argList.jjtGetChild(argIndex))) {
            methodIsApplicable=false;
            break;
          }
        }
      }
      if (methodIsApplicable) {
        selectedMethods.add(methodType);
      }
    }
 else {
      LOG.log(Level.FINE,"Method {0} couldn't be resolved",String.valueOf(methodType));
    }
  }
  return selectedMethods;
}
