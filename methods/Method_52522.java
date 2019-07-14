/** 
 * Most specific method selection. https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.2.5
 */
public static MethodType selectMostSpecificMethod(List<MethodType> selectedMethods){
  MethodType mostSpecific=selectedMethods.get(0);
  for (int methodIndex=1; methodIndex < selectedMethods.size(); ++methodIndex) {
    MethodType nextMethod=selectedMethods.get(methodIndex);
    if (checkSubtypeability(mostSpecific,nextMethod)) {
      if (checkSubtypeability(nextMethod,mostSpecific)) {
        mostSpecific=selectAmongMaximallySpecific(mostSpecific,nextMethod);
      }
 else {
        mostSpecific=nextMethod;
      }
    }
  }
  return mostSpecific;
}
