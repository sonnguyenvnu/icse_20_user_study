/** 
 * cache hmilyTransaction.
 * @param hmilyTransaction {@linkplain HmilyTransaction}
 */
public void cacheHmilyTransaction(final HmilyTransaction hmilyTransaction){
  LOADING_CACHE.put(hmilyTransaction.getTransId(),hmilyTransaction);
}
