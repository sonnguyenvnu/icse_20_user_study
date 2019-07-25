@Override public void init(ISubscriptionsRepository subscriptionsRepository){
  LOG.info("Initializing CTrie");
  ctrie=new CTrie();
  LOG.info("Initializing subscriptions store...");
  this.subscriptionsRepository=subscriptionsRepository;
  if (LOG.isTraceEnabled()) {
    LOG.trace("Reloading all stored subscriptions. SubscriptionTree = {}",dumpTree());
  }
  for (  Subscription subscription : this.subscriptionsRepository.listAllSubscriptions()) {
    LOG.debug("Re-subscribing {}",subscription);
    ctrie.addToTree(subscription);
  }
  if (LOG.isTraceEnabled()) {
    LOG.trace("Stored subscriptions have been reloaded. SubscriptionTree = {}",dumpTree());
  }
}
