@Override public List<InstanceAlertConfig> getByType(int type){
  try {
    return instanceAlertConfigDao.getByType(type);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
