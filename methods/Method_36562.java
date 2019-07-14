public Class<?> getInterfaceClass(){
  if (interfaceClass == null) {
    try {
      interfaceClass=this.getClass().getClassLoader().loadClass(interfaceType);
    }
 catch (    ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
catch (    NullPointerException e) {
      return null;
    }
  }
  return interfaceClass;
}
