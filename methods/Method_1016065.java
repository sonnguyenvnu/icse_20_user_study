public ModuleListBuilder exclude(Iterable<Class<? extends Module>> modules){
  for (  Class<? extends Module> module : modules) {
    excludes.add(module);
  }
  return this;
}
