synchronized void complete(T result,Throwable failure){
  if (isDone())   return;
  if (failure != null)   super.completeExceptionally(failure);
 else   super.complete(result);
}
