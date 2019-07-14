/** 
 * Opens a new transaction against all registered backend system wrapped in one  {@link BackendTransaction}.
 * @return
 * @throws BackendException
 */
public BackendTransaction beginTransaction(TransactionConfiguration configuration,KeyInformation.Retriever indexKeyRetriever) throws BackendException {
  StoreTransaction tx=storeManagerLocking.beginTransaction(configuration);
  CacheTransaction cacheTx=new CacheTransaction(tx,storeManagerLocking,bufferSize,maxWriteTime,configuration.hasEnabledBatchLoading());
  final Map<String,IndexTransaction> indexTx=new HashMap<>(indexes.size());
  for (  Map.Entry<String,IndexProvider> entry : indexes.entrySet()) {
    indexTx.put(entry.getKey(),new IndexTransaction(entry.getValue(),indexKeyRetriever.get(entry.getKey()),configuration,maxWriteTime));
  }
  return new BackendTransaction(cacheTx,configuration,storeFeatures,edgeStore,indexStore,txLogStore,maxReadTime,indexTx,threadPool);
}
