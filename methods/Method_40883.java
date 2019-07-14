void handleComplete(ExecutionResult result,ExecutionContext context){
  if (successListener != null && result.getSuccessAll())   successListener.handle(result,context.copy());
 else   if (failureListener != null && !result.getSuccessAll())   failureListener.handle(result,context.copy());
  if (completeListener != null)   completeListener.handle(result,context.copy());
}
