public TaskResult result(DiscoveryNode node,Exception error) throws IOException {
  return new TaskResult(taskInfo(node.getId(),true),error);
}
