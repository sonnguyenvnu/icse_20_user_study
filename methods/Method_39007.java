/** 
 * Creates execution array that will invoke all filters, actions and results in correct order.
 */
protected ActionWrapper[] createExecutionArray(){
  int totalInterceptors=(this.actionRuntime.getInterceptors() != null ? this.actionRuntime.getInterceptors().length : 0);
  int totalFilters=(this.actionRuntime.getFilters() != null ? this.actionRuntime.getFilters().length : 0);
  ActionWrapper[] executionArray=new ActionWrapper[totalFilters + 1 + totalInterceptors + 1];
  int index=0;
  if (totalFilters > 0) {
    System.arraycopy(actionRuntime.getFilters(),0,executionArray,index,totalFilters);
    index+=totalFilters;
  }
  executionArray[index++]=actionRequest -> {
    Object actionResult=actionRequest.invoke();
    ActionRequest.this.madvocController.render(ActionRequest.this,actionResult);
    return actionResult;
  }
;
  if (totalInterceptors > 0) {
    System.arraycopy(actionRuntime.getInterceptors(),0,executionArray,index,totalInterceptors);
    index+=totalInterceptors;
  }
  executionArray[index]=actionRequest -> {
    actionResult=invokeActionMethod();
    return actionResult;
  }
;
  return executionArray;
}
