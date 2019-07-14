/** 
 * Look for methods be method conversion. https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.2.3
 */
public static List<MethodType> selectMethodsSecondPhase(List<MethodType> methodsToSearch,ASTArgumentList argList){
  List<MethodType> selectedMethods=new ArrayList<>();
  final int argCount=argList == null ? 0 : argList.jjtGetNumChildren();
  for (int methodIndex=0; methodIndex < methodsToSearch.size(); ++methodIndex) {
    MethodType methodType=methodsToSearch.get(methodIndex);
    if (!methodType.isParameterized()) {
      throw new ResolutionFailedException();
    }
    if (getArity(methodType.getMethod()) == argCount) {
      boolean methodIsApplicable=true;
      for (int argIndex=0; argIndex < argCount; ++argIndex) {
        if (!isMethodConvertible(methodType.getParameterTypes().get(argIndex),(ASTExpression)argList.jjtGetChild(argIndex))) {
          methodIsApplicable=false;
          break;
        }
      }
      if (methodIsApplicable) {
        selectedMethods.add(methodType);
      }
    }
  }
  return selectedMethods;
}
