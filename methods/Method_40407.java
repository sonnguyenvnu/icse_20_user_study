ModuleType newModule(String name){
  ModuleType mt=new ModuleType(name,null,globaltable);
  nativeTypes.add(mt);
  return mt;
}
