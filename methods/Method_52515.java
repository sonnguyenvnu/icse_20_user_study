/** 
 * Look for methods be subtypeability. https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.2.2
 */
public static List<MethodType> selectMethodsFirstPhase(JavaTypeDefinition context,List<MethodType> methodsToSearch,ASTArgumentList argList){
  List<MethodType> selectedMethods=new ArrayList<>();
  final int argCount=argList == null ? 0 : argList.jjtGetNumChildren();
  outter:   for (int methodIndex=0; methodIndex < methodsToSearch.size(); ++methodIndex) {
    MethodType methodType=methodsToSearch.get(methodIndex);
    if (getArity(methodType.getMethod()) == argCount) {
      if (!methodType.isParameterized()) {
        Class<?>[] methodParameterTypes=methodType.getMethod().getParameterTypes();
        for (int argIndex=0; argIndex < argCount; ++argIndex) {
          if (((ASTExpression)argList.jjtGetChild(argIndex)).isStandAlonePrimitive()) {
            if (!methodParameterTypes[argIndex].isPrimitive()) {
              continue outter;
            }
          }
 else           if (methodParameterTypes[argIndex].isPrimitive()) {
            continue outter;
          }
        }
        methodType=parameterizeInvocation(context,methodType.getMethod(),argList);
        if (methodType == null) {
          continue;
        }
      }
      boolean methodIsApplicable=true;
      for (int argIndex=0; argIndex < argCount; ++argIndex) {
        if (!isSubtypeable(methodType.getParameterTypes().get(argIndex),(ASTExpression)argList.jjtGetChild(argIndex))) {
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
