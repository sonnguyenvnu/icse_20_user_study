@Override public AbstractObjectParser setSQLConfig(int count,int page,int position) throws Exception {
  if (isTable == false) {
    return this;
  }
  if (sqlConfig == null) {
    try {
      sqlConfig=newSQLConfig(false);
    }
 catch (    NotExistException e) {
      e.printStackTrace();
      return this;
    }
  }
  sqlConfig.setCount(count).setPage(page).setPosition(position);
  parser.onVerifyRole(sqlConfig);
  return this;
}
