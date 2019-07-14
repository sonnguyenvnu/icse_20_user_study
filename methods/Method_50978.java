public boolean isInstanceInitializer(){
  return ClassLoaderUtil.INIT.equals(name);
}
