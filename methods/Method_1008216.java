/** 
 * Use this method when the transport action should continue to run in the context of the current task
 */
public final void execute(Task task,Request request,ActionListener<Response> listener){
  ActionRequestValidationException validationException=request.validate();
  if (validationException != null) {
    listener.onFailure(validationException);
    return;
  }
  if (task != null && request.getShouldStoreResult()) {
    listener=new TaskResultStoringActionListener<>(taskManager,task,listener);
  }
  RequestFilterChain<Request,Response> requestFilterChain=new RequestFilterChain<>(this,logger);
  requestFilterChain.proceed(task,actionName,request,listener);
}
