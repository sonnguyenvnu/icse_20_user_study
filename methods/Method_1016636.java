@Override public void query(final Query query,final int chunkSize,final BiConsumer<Cancellable,QueryResult> onNext,final Runnable onComplete){
  query(query,chunkSize,onNext,onComplete,null);
}
