@Override public void start(EventBus eventBus){
  this.eventBus=eventBus;
  clientFactory.getShell().setAddButtonVisible(true);
  if (clearTaskList) {
    getView().clearList();
  }
  refreshTimer=new Timer(){
    @Override public void run(){
      refreshTaskList();
    }
  }
;
  List<TaskProxy> list=clientFactory.getTaskProxyLocalStorage().getTasks();
  setTasks(list);
  refreshTaskList();
}
