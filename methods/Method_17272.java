@Override public void write(K key,V value){
  subject.onNext(new SimpleImmutableEntry<>(key,value));
}
