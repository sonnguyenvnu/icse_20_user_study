@Nonnull @Override public DomainType create(){
  return findOrCreateEntity(EntityUtil.pathForDomainTypes(),UUID.randomUUID().toString(),(Map<String,Object>)null);
}
