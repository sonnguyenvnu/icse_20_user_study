void cancel(){
  if (!emitter.isDisposed()) {
    emitter.onComplete();
  }
}
