protected boolean entityFactoryIsEnabled(){
  if (entityFactory == null) {
    logger.warn("entityFactory is null!");
  }
  return null != entityFactory;
}
