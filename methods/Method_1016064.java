public ModuleListBuilder include(final Module m){
  ModuleProvider provider=new ModuleProvider(m);
  if (!m.getClass().isAnonymousClass()) {
    if (includes.containsKey(m.getClass())) {
      return this;
    }
    includes.put(m.getClass(),provider);
    for (    Class<? extends Module> dep : provider.getIncludeList()) {
      include(dep,false);
    }
    for (    Class<? extends Module> dep : provider.getExcludeList()) {
      exclude(dep);
    }
  }
  providers.add(provider);
  return this;
}
