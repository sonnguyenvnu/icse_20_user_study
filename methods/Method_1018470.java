/** 
 * Given a parent view to show itself in, start this App.
 * @param parentView where to show the app's widget
 */
public void run(HasWidgets.ForIsWidget parentView){
  activityManager.setDisplay(shell);
  parentView.add(shell);
  ActionEvent.register(eventBus,ActionNames.ADD_TASK,new ActionEvent.Handler(){
    @Override public void onAction(    ActionEvent event){
      placeController.goTo(TaskPlace.getTaskCreatePlace());
    }
  }
);
  eventBus.addHandler(ShowTaskEvent.TYPE,new ShowTaskEvent.Handler(){
    @Override public void onShowTask(    ShowTaskEvent event){
      TaskProxy task=event.getTask();
      placeController.goTo(TaskPlace.createTaskEditPlace(task.getId(),task));
    }
  }
);
  ActionEvent.register(eventBus,ActionNames.GO_HOME,new ActionEvent.Handler(){
    @Override public void onAction(    ActionEvent event){
      placeController.goTo(new TaskListPlace(false));
    }
  }
);
  ActionEvent.register(eventBus,ActionNames.TASK_SAVED,new ActionEvent.Handler(){
    @Override public void onAction(    ActionEvent event){
      placeController.goTo(new TaskListPlace(true));
    }
  }
);
  ActionEvent.register(eventBus,ActionNames.EDITING_CANCELED,new ActionEvent.Handler(){
    @Override public void onAction(    ActionEvent event){
      placeController.goTo(new TaskListPlace(false));
    }
  }
);
  GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){
    @Override public void onUncaughtException(    Throwable e){
      while (e instanceof UmbrellaException) {
        e=((UmbrellaException)e).getCauses().iterator().next();
      }
      String message=e.getMessage();
      if (message == null) {
        message=e.toString();
      }
      log.log(Level.SEVERE,"Uncaught exception",e);
      Window.alert("An unexpected error occurred: " + message);
    }
  }
);
  reloadOnAuthenticationFailure.register(eventBus);
  initBrowserHistory(historyMapper,historyHandler,new TaskListPlace(true));
}
