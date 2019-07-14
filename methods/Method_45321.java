public static <T>ImmutableList<T> toObjects(final ImmutableList<InputStream> streams,final Class<T> elementClass){
  final CollectionType type=DEFAULT_FACTORY.constructCollectionType(List.class,elementClass);
  return FluentIterable.from(streams).transformAndConcat(Jsons.<T>toObject(type)).toList();
}
