Connection start(){
  log.info("Starting PubSub connection");
  ProjectSubscriptionName subscriptionName=ProjectSubscriptionName.of(projectId,subscriptionId);
  FlowControlSettings flowControlSettings=FlowControlSettings.newBuilder().setMaxOutstandingElementCount(maxOutstandingElementCount).setMaxOutstandingRequestBytes(maxOutstandingRequestBytes).build();
  ExecutorProvider executorProvider=InstantiatingExecutorProvider.newBuilder().setExecutorThreadCount(threads).build();
  log.info("Subscribing to {}",subscriptionName);
  final Receiver receiver=new Receiver(consumer,reporter,errors,consumed);
  subscriber=Subscriber.newBuilder(subscriptionName,receiver).setFlowControlSettings(flowControlSettings).setParallelPullCount(threads).setExecutorProvider(executorProvider).setChannelProvider(channelProvider).setCredentialsProvider(credentialsProvider).build();
  subscriber.addListener(new Subscriber.Listener(){
    @Override public void failed(    Subscriber.State from,    Throwable failure){
      log.error("An error on subscriber happened (from state: " + from.name() + ")",failure);
      System.exit(1);
    }
  }
,MoreExecutors.directExecutor());
  subscriber.startAsync().awaitRunning();
  log.info("PubSub connection started");
  return this;
}
