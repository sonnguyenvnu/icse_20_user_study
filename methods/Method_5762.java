private ContentMetadataMutations checkAndSet(String name,Object value){
  editedValues.put(Assertions.checkNotNull(name),Assertions.checkNotNull(value));
  removedValues.remove(name);
  return this;
}
