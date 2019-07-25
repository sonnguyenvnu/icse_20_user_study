@Override public <O>RegistrySpec add(TypeToken<O> type,O object){
  entries.push(new DefaultRegistryEntry<>(type,object));
  return this;
}
