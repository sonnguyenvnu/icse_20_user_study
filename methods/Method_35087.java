public static <Raw,Key>boolean shouldReturnNetworkBeforeStale(BasePersister persister,StalePolicy stalePolicy,Key key){
  return stalePolicy == StalePolicy.NETWORK_BEFORE_STALE && persisterIsStale(key,persister);
}
