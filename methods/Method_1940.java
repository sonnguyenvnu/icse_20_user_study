private static void configureLoggingListeners(ImagePipelineConfig.Builder configBuilder){
  Set<RequestListener> requestListeners=new HashSet<>();
  requestListeners.add(new RequestLoggingListener());
  configBuilder.setRequestListeners(requestListeners);
}
