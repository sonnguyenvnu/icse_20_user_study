public IocMaking clone(String objectName){
  return new IocMaking(ioc,mirrors,context,objectMaker,vpms,objectName).setListeners(listeners);
}
