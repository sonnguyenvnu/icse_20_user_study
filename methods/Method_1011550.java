protected IValueProxy invoke(String name,String jniSignature,ClassType classType,final int options,final ThreadReference threadReference,Object[] args) throws EvaluationException {
  final Method method=classType.concreteMethodByName(name,jniSignature);
  if (method == null) {
    throw new InvalidEvaluatedExpressionException("Concrete method " + name + " with signature " + jniSignature + " not found in " + classType + ".");
  }
  final List<Value> argValues=MirrorUtil.getInstance().getValues(myValue.virtualMachine(),args);
  return EvaluationUtils.handleInvocationExceptions(new EvaluationUtils.ThreadInvocatable<IValueProxy>(threadReference){
    @Override public IValueProxy invoke() throws InvocationException, InvalidTypeException, ClassNotLoadedException, IncompatibleThreadStateException {
      Value result=getObjectValue().invokeMethod(threadReference,method,argValues,options);
      return MirrorUtil.getInstance().getValueProxy(result);
    }
  }
);
}
