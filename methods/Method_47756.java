@Override public void execute(Task task){
  task.onAttached(this);
  new CustomAsyncTask(task).execute();
}
