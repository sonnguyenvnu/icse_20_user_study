protected Reference buildReference(){
  return new ReferenceImpl(uniqueId,getInterfaceClass(),InterfaceMode.spring,jvmFirst);
}
