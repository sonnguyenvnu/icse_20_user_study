/** 
 * {@inheritDoc}
 */
@Override public void commit(boolean force){
  throw new UnsupportedOperationException("Manual commit is not allowed over a Spring managed SqlSession");
}
