@Nullable ModuleType newModule(String name){
  return new ModuleType(name,null,Analyzer.self.globaltable);
}
