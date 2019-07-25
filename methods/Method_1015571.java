public UUID copy(){
  return new SiteUUID(mostSigBits,leastSigBits,get(NAME),get(SITE_NAME));
}
