private void callFailureListener(ExecutionResult result){
  if (result.isComplete() && policy instanceof FailurePolicy) {
    FailurePolicy failurePolicy=(FailurePolicy)policy;
    if (failurePolicy.failureListener != null)     failurePolicy.failureListener.handle(result,execution);
  }
}
