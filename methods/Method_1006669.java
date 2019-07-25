@Override public ReadableDelivery populate(Delivery source,ReadableDelivery target,MerchantStore store,Language language) throws ConversionException {
  if (countryService == null) {
    throw new ConversionException("countryService must be set");
  }
  if (zoneService == null) {
    throw new ConversionException("zoneService must be set");
  }
  target.setLatitude(source.getLatitude());
  target.setLongitude(source.getLongitude());
  if (StringUtils.isNotBlank(source.getCity())) {
    target.setCity(source.getCity());
  }
  if (StringUtils.isNotBlank(source.getCompany())) {
    target.setCompany(source.getCompany());
  }
  if (StringUtils.isNotBlank(source.getAddress())) {
    target.setAddress(source.getAddress());
  }
  if (StringUtils.isNotBlank(source.getFirstName())) {
    target.setFirstName(source.getFirstName());
  }
  if (StringUtils.isNotBlank(source.getLastName())) {
    target.setLastName(source.getLastName());
  }
  if (StringUtils.isNotBlank(source.getPostalCode())) {
    target.setPostalCode(source.getPostalCode());
  }
  if (StringUtils.isNotBlank(source.getTelephone())) {
    target.setPhone(source.getTelephone());
  }
  target.setStateProvince(source.getState());
  if (source.getTelephone() == null) {
    target.setPhone(source.getTelephone());
  }
  target.setAddress(source.getAddress());
  if (source.getCountry() != null) {
    target.setCountry(source.getCountry().getIsoCode());
    try {
      Map<String,Country> countries=countryService.getCountriesMap(language);
      Country c=countries.get(source.getCountry().getIsoCode());
      if (c != null) {
        target.setCountryName(c.getName());
      }
    }
 catch (    ServiceException e) {
      throw new ConversionException(e);
    }
  }
  if (source.getZone() != null) {
    target.setZone(source.getZone().getCode());
    try {
      Map<String,Zone> zones=zoneService.getZones(language);
      Zone z=zones.get(source.getZone().getCode());
      if (z != null) {
        target.setProvinceName(z.getName());
      }
    }
 catch (    ServiceException e) {
      throw new ConversionException(e);
    }
  }
  return target;
}
