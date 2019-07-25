public Serializable save(TsBlackListEntity entity) throws Exception {
  Serializable t=super.save(entity);
  this.doAddBus(entity);
  return t;
}
