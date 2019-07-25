private void unsubscribe(Session session){
  for (  Subscription existingSub : session.getSubscriptions()) {
    subscriptionsDirectory.removeSubscription(existingSub.getTopicFilter(),session.getClientID());
  }
}
