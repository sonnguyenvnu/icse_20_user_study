public void write(HystrixCommandExecutionStarted event){
  writeOnlySubject.onNext(event);
}
