@Override public <T>void makeRestCall(@NonNull Observable<T> observable,@NonNull Consumer<T> onNext){
  makeRestCall(observable,onNext,true);
}
