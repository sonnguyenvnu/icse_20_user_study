@PostConstruct void init(){
  this.rootName=((CMSManager)cacheManager).getRootName();
  LOGGER.info("init " + getClass().getName() + " setting root" + this.rootName);
}
