public void fail(String task){
  if (tasks.containsKey(task)) {
    tasks.put(task,false);
  }
}
