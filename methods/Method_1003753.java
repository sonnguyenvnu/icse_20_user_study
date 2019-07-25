/** 
 * {@inheritDoc}
 */
@Override public void accept(ExecResult<? extends T> result){
  if (ref.compareAndSet(null,result)) {
    drain();
  }
 else {
    throw new AlreadySuppliedException("promised has already been completed with " + ref.get());
  }
}
