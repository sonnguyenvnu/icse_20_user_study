/** 
 * ????????
 * @param proxy
 */
protected void addProxy(TransactionProxy proxy){
  connectionMap.put(switcher().currentDataSourceId(),proxy);
}
