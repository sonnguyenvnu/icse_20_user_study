@Override void unregisterForActivityResults(@NonNull String instanceId){
  if (hostController != null && hostController.getRouter() != null) {
    hostController.getRouter().unregisterForActivityResults(instanceId);
  }
}
