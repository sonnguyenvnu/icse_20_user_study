/** 
 * Use this method when the transport action call should result in creation of a new task associated with the call. This is a typical behavior.
 */
public final Task execute(Request request,ActionListener<Response> listener){
  Task task=taskManager.register("transport",actionName,request);
  if (task == null) {
    execute(null,request,listener);
  }
 else {
    execute(task,request,new ActionListener<Response>(){
      @Override public void onResponse(      Response response){
        taskManager.unregister(task);
        listener.onResponse(response);
      }
      @Override public void onFailure(      Exception e){
        taskManager.unregister(task);
        listener.onFailure(e);
      }
    }
);
  }
  return task;
}
