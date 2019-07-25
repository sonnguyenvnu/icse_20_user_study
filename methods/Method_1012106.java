private <E extends Exception>AbstractModule recover(E e,AbstractModule result) throws E {
  result.getModuleSourceDir().delete();
  throw e;
}
