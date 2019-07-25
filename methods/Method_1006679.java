@Override public ReadableCountry populate(Country source,ReadableCountry target,MerchantStore store,Language language) throws ConversionException {
  if (target == null) {
    target=new ReadableCountry();
  }
  target.setId(new Long(source.getId()));
  target.setCode(source.getIsoCode());
  target.setSupported(source.getSupported());
  if (!CollectionUtils.isEmpty(source.getDescriptions())) {
    target.setName(source.getDescriptions().iterator().next().getName());
  }
  if (!CollectionUtils.isEmpty(source.getZones())) {
    for (    Zone z : source.getZones()) {
      ReadableZone readableZone=new ReadableZone();
      readableZone.setCountryCode(target.getCode());
      readableZone.setId(z.getId());
      if (!CollectionUtils.isEmpty(z.getDescriptions())) {
        for (        ZoneDescription d : z.getDescriptions()) {
          if (d.getLanguage().getId() == language.getId()) {
            readableZone.setName(d.getName());
            continue;
          }
        }
      }
      target.getZones().add(readableZone);
    }
  }
  return target;
}
