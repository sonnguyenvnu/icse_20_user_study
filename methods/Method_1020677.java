public <T>com.mongodb.client.MongoCollection<T> wrap(com.mongodb.client.MongoCollection<T> collection){
  return collection.withCodecRegistry(codecRegistry);
}
