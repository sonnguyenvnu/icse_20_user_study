@Override public Customer populate(Address source,Customer target,MerchantStore store,Language language) throws ConversionException {
  target.getBilling().setFirstName(source.getFirstName());
  target.getBilling().setLastName(source.getLastName());
  if (StringUtils.isNotBlank(source.getAddress())) {
    target.getBilling().setAddress(source.getAddress());
  }
  if (StringUtils.isNotBlank(source.getCity())) {
    target.getBilling().setCity(source.getCity());
  }
  if (StringUtils.isNotBlank(source.getCompany())) {
    target.getBilling().setCompany(source.getCompany());
  }
  if (StringUtils.isNotBlank(source.getPhone())) {
    target.getBilling().setTelephone(source.getPhone());
  }
  if (StringUtils.isNotBlank(source.getPostalCode())) {
    target.getBilling().setPostalCode(source.getPostalCode());
  }
  if (StringUtils.isNotBlank(source.getStateProvince())) {
    target.getBilling().setState(source.getStateProvince());
  }
  return target;
}
