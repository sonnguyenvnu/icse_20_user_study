static FilterFactory createFilterFactory(Class<? extends FilterFactory> filterFactoryClass) throws FilterNotCreatedException {
  try {
    return filterFactoryClass.getConstructor().newInstance();
  }
 catch (  Exception e) {
    throw new FilterNotCreatedException(e);
  }
}
