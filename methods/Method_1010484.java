PropertyInfo find(@NotNull SPropertyId id){
  assert myProperties.containsKey(id);
  return myProperties.get(id);
}
