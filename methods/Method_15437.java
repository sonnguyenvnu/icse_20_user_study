@Override public String getVisitorIdKey(SQLConfig config){
  return Controller.USER_.equals(config.getTable()) || Controller.PRIVACY_.equals(config.getTable()) ? Controller.ID : Controller.USER_ID;
}
