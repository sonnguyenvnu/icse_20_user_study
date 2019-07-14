@Override public void emitNext(E event){
  lifecycleSubject.onNext(event);
}
