/** 
 * Constructor map should contain a key for each private constructor, and maps to a List which contains all called constructors of that key. marks dangerous if call dangerous private constructor we ignore all non-private constructors here. That is, the map passed in should not contain any non-private constructors. we return boolean in order to limit the number of passes through this method but it seems as if we can forgo that and just process it till its done.
 */
private boolean evaluateDangerOfConstructors2(Map<ConstructorHolder,List<MethodInvocation>> classConstructorMap){
  boolean found=false;
  for (  ConstructorHolder ch : classConstructorMap.keySet()) {
    ConstructorInvocation calledC=ch.getCalledConstructor();
    if (calledC == null || ch.isDangerous()) {
      continue;
    }
    int cCount=calledC.getArgumentCount();
    for (Iterator<ConstructorHolder> innerConstIter=classConstructorMap.keySet().iterator(); innerConstIter.hasNext() && !ch.isDangerous(); ) {
      ConstructorHolder h2=innerConstIter.next();
      if (h2.isDangerous()) {
        int matchConstArgCount=h2.getASTConstructorDeclaration().getParameterCount();
        List<String> parameterTypes=getMethodDeclaratorParameterTypes(h2.getASTConstructorDeclaration());
        if (matchConstArgCount == cCount && parameterTypes.equals(calledC.getArgumentTypes())) {
          ch.setDangerous(true);
          found=true;
        }
      }
    }
  }
  return found;
}
