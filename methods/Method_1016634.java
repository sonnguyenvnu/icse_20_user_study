/** 
 * {@inheritDoc}
 */
@Override public void query(final Query query,final int chunkSize,final Consumer<QueryResult> onNext){
  query(query,chunkSize,onNext,() -> {
  }
);
}
