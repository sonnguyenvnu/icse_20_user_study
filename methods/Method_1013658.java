@Override public void register(Subscriber subscriber){
  if (subscriber instanceof AlertEntitySubscriber) {
    alertEntitySubscribers.add((AlertEntitySubscriber)subscriber);
  }
}
