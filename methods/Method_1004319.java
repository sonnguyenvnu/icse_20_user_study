@NotNull @Override public ApolloStoreOperation<Boolean> remove(@NotNull final CacheKey cacheKey,final boolean cascade){
  checkNotNull(cacheKey,"cacheKey == null");
  return new ApolloStoreOperation<Boolean>(dispatcher){
    @Override protected Boolean perform(){
      return writeTransaction(new Transaction<WriteableStore,Boolean>(){
        @Override public Boolean execute(        WriteableStore cache){
          return optimisticCache.remove(cacheKey,cascade);
        }
      }
);
    }
  }
;
}
