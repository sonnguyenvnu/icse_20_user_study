@Override public SModule resolve(@NotNull SModuleReference reference){
  if (myResolveScope == null) {
    final Collection<SModule> allVisible;
    if (myRespectVisible) {
      allVisible=new GlobalModuleDependenciesManager(myModules).getModules(Deptype.VISIBLE);
    }
 else {
      allVisible=myModules;
    }
    myResolveScope=new HashMap<>(allVisible.size() * 4 / 3);
    for (    SModule m : allVisible) {
      myResolveScope.put(m.getModuleReference(),m);
    }
  }
  return myResolveScope.get(reference);
}
