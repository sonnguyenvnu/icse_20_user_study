public static <Raw,Parsed,Key>StoreRoom<Parsed,Key> from(Fetcher<Raw,Key> fetcher,RoomPersister<Raw,Parsed,Key> persister){
  return new RealStoreRoom<>(fetcher,persister);
}
