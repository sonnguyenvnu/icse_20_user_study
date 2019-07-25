void publish(int index,Task task){
  if (index < 0)   index=next();
  count.incrementAndGet();
  task.setTid(index);
  exes[index].publish(task);
}
