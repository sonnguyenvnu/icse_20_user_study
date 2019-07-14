@Nullable public Future<?> put(Diff diff){
  if (refactoredPaths.add(diff.getRelevantFileName())) {
    runState.incrementAndGet();
    return workerService.submit(new Task(diff));
  }
  return null;
}
