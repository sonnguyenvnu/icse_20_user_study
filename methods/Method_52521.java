/** 
 * Searches a list of methods by trying the three phases of method overload resolution. https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.2
 */
public static JavaTypeDefinition getBestMethodReturnType(JavaTypeDefinition context,List<MethodType> methods,ASTArgumentList arguments){
  try {
    List<MethodType> selectedMethods=selectMethodsFirstPhase(context,methods,arguments);
    if (!selectedMethods.isEmpty()) {
      return selectMostSpecificMethod(selectedMethods).getReturnType();
    }
    selectedMethods=selectMethodsSecondPhase(methods,arguments);
    if (!selectedMethods.isEmpty()) {
      return selectMostSpecificMethod(selectedMethods).getReturnType();
    }
    selectedMethods=selectMethodsThirdPhase(methods,arguments);
    if (!selectedMethods.isEmpty()) {
      return selectMostSpecificMethod(selectedMethods).getReturnType();
    }
    return null;
  }
 catch (  ResolutionFailedException e) {
    return null;
  }
}
