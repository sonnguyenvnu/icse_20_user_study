public T create(){
  T ret=null;
  try {
    ret=instantiateReference();
    resolveDependencies(ret);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Creates bean [name=" + name + "] failed",e);
  }
  return ret;
}
