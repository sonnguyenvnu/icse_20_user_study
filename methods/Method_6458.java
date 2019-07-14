private void addOperationToQueue(FileLoadOperation operation,LinkedList<FileLoadOperation> queue){
  int priority=operation.getPriority();
  if (priority > 0) {
    int index=queue.size();
    for (int a=0, size=queue.size(); a < size; a++) {
      FileLoadOperation queuedOperation=queue.get(a);
      if (queuedOperation.getPriority() < priority) {
        index=a;
        break;
      }
    }
    queue.add(index,operation);
  }
 else {
    queue.add(operation);
  }
}
