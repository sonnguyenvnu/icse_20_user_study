@Override public void call(final Iterator<Element> elements) throws Exception {
  final TaskContext taskContext=TaskContext.get();
  if (null == taskContext) {
    throw new OperationException("Method call(Iterator<Element>) should only be called from within a Spark job");
  }
  call(elements,taskContext.partitionId(),taskContext.taskAttemptId());
}
