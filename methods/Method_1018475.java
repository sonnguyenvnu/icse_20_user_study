public void start(EventBus newEventBus){
  this.eventBus=newEventBus;
  clientFactory.getShell().setAddButtonVisible(false);
  if (task == null) {
    task=clientFactory.getTaskProxyLocalStorage().getTask(taskId);
  }
  if (task == null) {
    clientFactory.getRequestFactory().taskRequest().findTask(this.taskId).fire(new Receiver<TaskProxy>(){
      @Override public void onSuccess(      TaskProxy response){
        if (isDead) {
          return;
        }
        if (response == null) {
          Window.alert("The task with id '" + taskId + "' could not be found." + " Please select a different task from the task list.");
          ActionEvent.fire(eventBus,ActionNames.EDITING_CANCELED);
          return;
        }
        task=response;
        getView().getEditorDriver().edit(response);
      }
    }
);
  }
 else {
    getView().getEditorDriver().edit(task);
  }
}
