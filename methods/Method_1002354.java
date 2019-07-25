public void confirm(CompensableInvocation invocation) throws RuntimeException {
  String identifier=(String)invocation.getIdentifier();
  String confirmableKey=invocation.getConfirmableKey();
  Method method=this.getCompensableMethod(invocation);
  Object[] args=invocation.getArgs();
  if (invocation.isSimplified()) {
    Object instance=this.applicationContext.getBean(identifier);
    this.confirmSimplified(method,instance,args);
  }
 else {
    Object instance=this.applicationContext.getBean(confirmableKey);
    this.confirmComplicated(method,instance,args);
  }
}
