/** 
 * Unregister the task
 */
public Task unregister(Task task){
  logger.trace("unregister task for id: {}",task.getId());
  if (task instanceof CancellableTask) {
    CancellableTaskHolder holder=cancellableTasks.remove(task.getId());
    if (holder != null) {
      holder.finish();
      return holder.getTask();
    }
 else {
      return null;
    }
  }
 else {
    return tasks.remove(task.getId());
  }
}
