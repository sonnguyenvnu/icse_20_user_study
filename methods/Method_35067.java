void notifySubscribers(Parsed data,Key key){
  subject.onNext(new AbstractMap.SimpleEntry<>(key,data));
}
