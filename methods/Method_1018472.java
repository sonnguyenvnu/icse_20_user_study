@Override protected void dispatch(TaskListUpdateEvent.Handler handler){
  handler.onTaskListUpdated(this);
}
