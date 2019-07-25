@Override public <T>Mono<T> save(T entity,@Nullable String index,@Nullable String type){
  Assert.notNull(entity,"Entity must not be null!");
  AdaptibleEntity<T> adaptableEntity=operations.forEntity(entity,converter.getConversionService());
  return doIndex(entity,adaptableEntity,index,type).map(it -> {
    return adaptableEntity.populateIdIfNecessary(it.getId());
  }
);
}
