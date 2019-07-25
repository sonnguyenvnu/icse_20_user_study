public void schedule(int index,Task task){
  assert index < 0 : "attempt to pin task to FJS";
  publish(task);
}
