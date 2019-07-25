@Nullable public Invocation dequeue(Object identifier){
synchronized (queues) {
    Queue<Invocation> queue=queues.get(identifier);
    if (queue != null) {
      return queue.poll();
    }
  }
  return null;
}
