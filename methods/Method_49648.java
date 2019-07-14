/** 
 * Solr handles all transactions on the server-side. That means all commit, optimize, or rollback applies since the last commit/optimize/rollback. Solr documentation recommends best way to update Solr is in one process to avoid race conditions.
 * @return New Transaction Handle
 */
@Override public BaseTransactionConfigurable beginTransaction(BaseTransactionConfig config){
  return new DefaultTransaction(config);
}
