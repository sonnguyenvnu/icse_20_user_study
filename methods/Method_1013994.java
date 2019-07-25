@Override public void enqueue(Invocation invocation){
synchronized (queues) {
    Queue<Invocation> queue=queues.get(invocation.getIdentifier());
    if (queue == null) {
      queue=new LinkedList<>();
      queues.put(invocation.getIdentifier(),queue);
    }
    queue.add(invocation);
  }
  trigger(invocation.getIdentifier());
}
