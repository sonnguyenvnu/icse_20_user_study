public IocObject load(IocLoading loading,String name) throws ObjectLoadException {
  reload();
  return objs.get(name);
}
