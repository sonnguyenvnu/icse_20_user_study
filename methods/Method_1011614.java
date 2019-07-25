public JavaModulesClosure closure(boolean reexportOnly){
  moduleClosure(initial,reexportOnly);
  modules.remove(initial);
  return this;
}
