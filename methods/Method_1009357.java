@Override public TypeMap<S,D> include(Class<? super D> baseDestinationType){
  if (provider == null)   provider=new Provider<D>(){
    @Override public D get(    ProvisionRequest<D> request){
      return Objects.instantiate(destinationType);
    }
  }
;
  configuration.typeMapStore.put(sourceType,baseDestinationType,this);
  return this;
}
