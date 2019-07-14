@Override public synchronized E load(){
  if (!file.exists()) {
    try {
      return clazz.newInstance();
    }
 catch (    final InstantiationException|IllegalAccessException ex) {
      throw new JobConsoleException(ex);
    }
  }
  try {
    @SuppressWarnings("unchecked") E result=(E)jaxbContext.createUnmarshaller().unmarshal(file);
    return result;
  }
 catch (  final JAXBException ex) {
    throw new JobConsoleException(ex);
  }
}
