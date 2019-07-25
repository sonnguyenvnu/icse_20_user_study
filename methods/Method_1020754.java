@Override public Subscriber<? super T> apply(Subscriber<? super T> subscriber){
  ValveMainSubscriber<T> parent=new ValveMainSubscriber<T>(subscriber,bufferSize,defaultOpen);
  subscriber.onSubscribe(parent);
  other.subscribe(parent.other);
  return parent;
}
