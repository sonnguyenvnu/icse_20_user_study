@Override public synchronized Request poll(Task task){
  if (!inited.get()) {
    init(task);
  }
  fileCursorWriter.println(cursor.incrementAndGet());
  return queue.poll();
}
