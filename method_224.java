@Override public OpResult _XXXXX_(Kafka2TupleMetadata dataSource){
  return handler.addOrReplace(Kafka2TupleMetadata.class.getSimpleName(),dataSource);
}