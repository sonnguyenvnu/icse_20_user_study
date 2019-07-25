@Override public boolean process(Subscriber subscriber,ActorSystem.Actor<Subscriber> self){
  subscriber.checkStatus();
  return true;
}
