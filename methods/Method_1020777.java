void remove(WorkDisposable d){
  consumer.compareAndSet(d,null);
}
