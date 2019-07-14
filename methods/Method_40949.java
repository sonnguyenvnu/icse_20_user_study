private void callSuccessListener(ExecutionResult result){
  if (result.isComplete() && policy instanceof FailurePolicy) {
    FailurePolicy failurePolicy=(FailurePolicy)policy;
    if (failurePolicy.successListener != null)     failurePolicy.successListener.handle(result,execution);
  }
}
