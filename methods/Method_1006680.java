@Override public ReadableZone populate(Zone source,ReadableZone target,MerchantStore store,Language language) throws ConversionException {
  if (target == null) {
    target=new ReadableZone();
  }
  target.setId(source.getId());
  target.setCode(source.getCode());
  target.setCountryCode(source.getCountry().getIsoCode());
  if (!CollectionUtils.isEmpty(source.getDescriptions())) {
    for (    ZoneDescription d : source.getDescriptions()) {
      if (d.getLanguage().getId() == language.getId()) {
        target.setName(d.getName());
        continue;
      }
    }
  }
  return target;
}
