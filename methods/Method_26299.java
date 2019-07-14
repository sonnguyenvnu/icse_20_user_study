private static boolean hasOtherInvocationsOrAssignments(MethodInvocationTree methodInvocationTree,TryTree tryTree,VisitorState state){
  AtomicInteger count=new AtomicInteger(0);
  Type threadType=state.getTypeFromString("java.lang.Thread");
  new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree tree,    Void unused){
      if (!tree.equals(methodInvocationTree)) {
        count.incrementAndGet();
      }
      return super.visitMethodInvocation(tree,null);
    }
    @Override public Void visitAssignment(    AssignmentTree tree,    Void unused){
      if (isSubtype(getType(tree.getVariable()),threadType,state)) {
        count.incrementAndGet();
      }
      return super.visitAssignment(tree,null);
    }
  }
.scan(tryTree.getBlock(),null);
  return count.get() > 0;
}
