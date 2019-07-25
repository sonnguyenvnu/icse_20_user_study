@Override public void produce(BoundedInMemoryQueue<I,?> queue) throws Exception {
  logger.info("starting to buffer records");
  while (inputIterator.hasNext()) {
    queue.insertRecord(inputIterator.next());
  }
  logger.info("finished buffering records");
}
