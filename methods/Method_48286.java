/** 
 * Returns true if the specified key-column pair exists in the store.
 * @param store  Store
 * @param key    Key
 * @param column Column
 * @param txh    Transaction
 * @return TRUE, if key has at least one column-value pair, else FALSE
 */
public static boolean containsKeyColumn(KeyColumnValueStore store,StaticBuffer key,StaticBuffer column,StoreTransaction txh) throws BackendException {
  return get(store,key,column,txh) != null;
}
