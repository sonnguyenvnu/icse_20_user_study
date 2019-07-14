protected Optional<ConfigContent> getConfigContent(String configId,String key){
  ConfigEntity entity=selectByPk(configId);
  if (entity == null) {
    return Optional.empty();
  }
  return Optional.ofNullable(entity.get(key));
}
