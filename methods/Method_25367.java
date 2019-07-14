/** 
 * Returns true if the tree contains a method invocation that looks like a test assertion. 
 */
public static boolean containsTestMethod(Tree tree){
  return firstNonNull(tree.accept(new TreeScanner<Boolean,Void>(){
    @Override public Boolean visitMethodInvocation(    MethodInvocationTree node,    Void unused){
      String name=getSymbol(node).getSimpleName().toString();
      return name.contains("assert") || name.contains("verify") || name.contains("check") || name.contains("fail") || name.contains("expect") || firstNonNull(super.visitMethodInvocation(node,null),false);
    }
    @Override public Boolean reduce(    Boolean a,    Boolean b){
      return firstNonNull(a,false) || firstNonNull(b,false);
    }
  }
,null),false);
}
