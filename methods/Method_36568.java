protected Service buildService(){
  return new ServiceImpl(uniqueId,getInterfaceClass(),InterfaceMode.spring,ref);
}
