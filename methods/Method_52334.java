/** 
 * marks constructors dangerous if they call any dangerous methods Requires only a single pass as methods are already marked TODO optimize by having methods already evaluated somehow!?
 */
private void evaluateDangerOfConstructors1(Map<ConstructorHolder,List<MethodInvocation>> classConstructorMap,Set<MethodHolder> evaluatedMethods){
  for (  Map.Entry<ConstructorHolder,List<MethodInvocation>> entry : classConstructorMap.entrySet()) {
    ConstructorHolder ch=entry.getKey();
    if (!ch.isDangerous()) {
      List<MethodInvocation> calledMeths=entry.getValue();
      for (Iterator<MethodInvocation> calledMethsIter=calledMeths.iterator(); calledMethsIter.hasNext() && !ch.isDangerous(); ) {
        MethodInvocation meth=calledMethsIter.next();
        String methName=meth.getName();
        int methArgCount=meth.getArgumentCount();
        for (        MethodHolder h : evaluatedMethods) {
          if (h.isDangerous()) {
            String matchName=h.getASTMethodDeclarator().getImage();
            int matchParamCount=h.getASTMethodDeclarator().getParameterCount();
            List<String> parameterTypes=getMethodDeclaratorParameterTypes(h.getASTMethodDeclarator());
            if (methName.equals(matchName) && methArgCount == matchParamCount && parameterTypes.equals(meth.getArgumentTypes())) {
              ch.setDangerous(true);
              break;
            }
          }
        }
      }
    }
  }
}
