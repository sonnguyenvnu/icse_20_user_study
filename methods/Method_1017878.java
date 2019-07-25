@Override public <C extends Serializable>Alert create(URI type,String subtype,Alert.Level level,String description,C content){
  GenericAlert alert=new GenericAlert(type,subtype,level,description,content);
  DateTime createdTime=alert.getEvents().get(0).getChangeTime();
  addAlert(alert,createdTime);
  return alert;
}
