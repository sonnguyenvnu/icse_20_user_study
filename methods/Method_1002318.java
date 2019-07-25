@Override public void drain(final Consumer<E> c,final WaitStrategy w,final ExitCondition exit){
  final E[] buffer=this.buffer;
  final long mask=this.mask;
  long consumerIndex=this.lpConsumerIndex();
  int counter=0;
  while (exit.keepRunning()) {
    for (int i=0; i < 4096; i++) {
      final long offset=calcElementOffset(consumerIndex,mask);
      final E e=lvElement(buffer,offset);
      if (null == e) {
        counter=w.idle(counter);
        continue;
      }
      consumerIndex++;
      counter=0;
      soElement(buffer,offset,null);
      soConsumerIndex(consumerIndex);
      c.accept(e);
    }
  }
}
