/** 
 * Check the methods called on this class by each of the methods on this class. If a method calls an unsafe method, mark the calling method as unsafe. This changes the list of unsafe methods which necessitates another pass. Keep passing until you make a clean pass in which no methods are changed to unsafe. For speed it is possible to limit the number of passes. <p/> Impossible to tell type of arguments to method, so forget method matching on types. just use name and num of arguments. will be some false hits, but oh well. TODO investigate limiting the number of passes through config.
 */
private boolean evaluateDangerOfMethods(Map<MethodHolder,List<MethodInvocation>> classMethodMap){
  boolean found=false;
  for (  Map.Entry<MethodHolder,List<MethodInvocation>> entry : classMethodMap.entrySet()) {
    MethodHolder h=entry.getKey();
    List<MethodInvocation> calledMeths=entry.getValue();
    for (Iterator<MethodInvocation> calledMethsIter=calledMeths.iterator(); calledMethsIter.hasNext() && !h.isDangerous(); ) {
      MethodInvocation meth=calledMethsIter.next();
      for (      MethodHolder h3 : classMethodMap.keySet()) {
        if (h3.isDangerous()) {
          String matchMethodName=h3.getASTMethodDeclarator().getImage();
          int matchMethodParamCount=h3.getASTMethodDeclarator().getParameterCount();
          List<String> parameterTypes=getMethodDeclaratorParameterTypes(h3.getASTMethodDeclarator());
          if (matchMethodName.equals(meth.getName()) && matchMethodParamCount == meth.getArgumentCount() && parameterTypes.equals(meth.getArgumentTypes())) {
            h.setDangerous();
            h.setCalledMethod(matchMethodName);
            found=true;
            break;
          }
        }
      }
    }
  }
  return found;
}
