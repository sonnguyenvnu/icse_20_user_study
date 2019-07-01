@Override public SiteEntity _XXXXX_(SiteEntity entity){
  Preconditions.checkNotNull(entity.getSiteId(),"SiteId is null: " + entity.getSiteId());
  if (siteId2EntityMap.containsKey(entity.getSiteId())) {
    throw new IllegalArgumentException("Duplicated siteId: " + entity.getSiteId());
  }
  entity.ensureDefault();
  siteId2EntityMap.put(entity.getSiteId(),entity);
  return entity;
}