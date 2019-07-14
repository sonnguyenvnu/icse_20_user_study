@Override public long open(DataSpec dataSpec) throws IOException {
  priorityTaskManager.proceedOrThrow(priority);
  return upstream.open(dataSpec);
}
