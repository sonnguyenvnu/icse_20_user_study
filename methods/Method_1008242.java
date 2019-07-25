@SuppressWarnings("unchecked") public <T extends IndexMetaData.Custom>T custom(String type){
  return (T)customs.get(type);
}
