public boolean contains(String clientId){
  for (  Subscription sub : this.subscriptions) {
    if (sub.clientId.equals(clientId)) {
      return true;
    }
  }
  return false;
}
