public boolean isStaticInitializer(){
  return ClassLoaderUtil.CLINIT.equals(name);
}
