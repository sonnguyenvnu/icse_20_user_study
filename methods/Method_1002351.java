public void register(CompensableInvocation invocation){
  Thread current=Thread.currentThread();
  Stack<CompensableInvocation> stack=this.invocationMap.get(current);
  if (stack == null) {
    stack=new Stack<CompensableInvocation>();
    this.invocationMap.put(current,stack);
  }
  stack.push(invocation);
}
