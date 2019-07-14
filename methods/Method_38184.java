/** 
 * Builds new transaction instance.
 */
@Override protected JtxTransaction createNewTransaction(final JtxTransactionMode tm,final Object scope,final boolean active){
  return new DbJtxTransaction(this,tm,scope,active);
}
