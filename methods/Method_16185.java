@Override public DataAccessEntity clone(){
  DataAccessEntity target=new DataAccessEntity();
  target.setDescribe(getDescribe());
  target.setAction(getAction());
  target.setConfig(getConfig());
  target.setType(getType());
  return target;
}
