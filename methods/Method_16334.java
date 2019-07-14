protected T find(Object value){
  return allOptionSupplier.get().stream().filter(e -> e.eq(value)).findFirst().orElseGet(() -> orElseGet.apply(value));
}
