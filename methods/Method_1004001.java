/** 
 * {@inheritDoc}
 */
@Override public void rollback(boolean force){
  throw new UnsupportedOperationException("Manual rollback is not allowed over a Spring managed SqlSession");
}
