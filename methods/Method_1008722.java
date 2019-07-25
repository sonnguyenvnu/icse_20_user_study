public TaskResult result(DiscoveryNode node,ActionResponse response) throws IOException {
  if (response instanceof ToXContent) {
    return new TaskResult(taskInfo(node.getId(),true),(ToXContent)response);
  }
 else {
    throw new IllegalStateException("response has to implement ToXContent to be able to store the results");
  }
}
