@Override public MerchantStore populate(PersistableMerchantStore source,MerchantStore target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(source,"PersistableMerchantStore mst not be null");
  if (target == null) {
    target=new MerchantStore();
  }
  target.setCode(source.getCode());
  if (source.getId() != 0) {
    target.setId(source.getId());
  }
  target.setDateBusinessSince(source.getInBusinessSince());
  if (source.getDimension() != null) {
    target.setSeizeunitcode(source.getDimension().name());
  }
  if (source.getWeight() != null) {
    target.setWeightunitcode(source.getWeight().name());
  }
  target.setCurrencyFormatNational(source.isCurrencyFormatNational());
  target.setStorename(source.getName());
  target.setStorephone(source.getPhone());
  target.setStoreEmailAddress(source.getEmail());
  target.setUseCache(source.isUseCache());
  try {
    if (!StringUtils.isEmpty(source.getDefaultLanguage())) {
      Language l=languageService.getByCode(source.getDefaultLanguage());
      target.setDefaultLanguage(l);
    }
    if (!StringUtils.isEmpty(source.getCurrency())) {
      Currency c=currencyService.getByCode(source.getCurrency());
      target.setCurrency(c);
    }
 else {
      target.setCurrency(currencyService.getByCode(Constants.DEFAULT_CURRENCY.getCurrencyCode()));
    }
    List<String> languages=source.getSupportedLanguages();
    if (!CollectionUtils.isEmpty(languages)) {
      for (      String lang : languages) {
        Language ll=languageService.getByCode(lang);
        target.getLanguages().add(ll);
      }
    }
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
  PersistableAddress address=source.getAddress();
  if (address != null) {
    Country country;
    try {
      country=countryService.getByCode(address.getCountry());
      Zone zone=zoneService.getByCode(address.getStateProvince());
      if (zone != null) {
        target.setZone(zone);
      }
 else {
        target.setStorestateprovince(address.getStateProvince());
      }
      target.setStoreaddress(address.getAddress());
      target.setStorecity(address.getCity());
      target.setCountry(country);
      target.setStorepostalcode(address.getPostalCode());
    }
 catch (    ServiceException e) {
      throw new ConversionException(e);
    }
  }
  return target;
}
