@Override public ReadableOptin populate(Optin source,ReadableOptin target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(store,"MerchantStore cannot be null");
  Validate.notNull(source,"Optin cannot be null");
  if (target == null) {
    target=new ReadableOptin();
  }
  target.setCode(source.getCode());
  target.setDescription(source.getDescription());
  target.setEndDate(source.getEndDate());
  target.setId(source.getId());
  target.setOptinType(source.getOptinType().name());
  target.setStartDate(source.getStartDate());
  target.setStore(store.getCode());
  return target;
}
