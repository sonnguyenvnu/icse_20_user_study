/** 
 * {@inheritDoc}
 */
@Override public void query(final Query query,final int chunkSize,final BiConsumer<Cancellable,QueryResult> onNext){
  query(query,chunkSize,onNext,() -> {
  }
);
}
