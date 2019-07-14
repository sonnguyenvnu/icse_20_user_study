private void flushTaskWithDeadline(Task task,long deadlineNs){
  long taskDeadlineNs=task.immediate ? RecyclerView.FOREVER_NS : deadlineNs;
  RecyclerView.ViewHolder holder=prefetchPositionWithDeadline(task.view,task.position,taskDeadlineNs);
  if (holder != null && holder.mNestedRecyclerView != null && holder.isBound() && !holder.isInvalid()) {
    prefetchInnerRecyclerViewWithDeadline(holder.mNestedRecyclerView.get(),deadlineNs);
  }
}
