private static void addCalledMethodsOfNodeImpl(List<ASTPrimaryExpression> expressions,List<MethodInvocation> calledMethods,String className){
  for (  ASTPrimaryExpression ape : expressions) {
    MethodInvocation meth=findMethod(ape,className);
    if (meth != null) {
      calledMethods.add(meth);
    }
  }
}
