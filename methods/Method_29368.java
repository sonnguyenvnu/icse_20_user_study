@Override public List<AppDataMigrateStatus> search(AppDataMigrateSearch appDataMigrateSearch){
  try {
    return appDataMigrateStatusDao.search(appDataMigrateSearch);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyList();
  }
}
