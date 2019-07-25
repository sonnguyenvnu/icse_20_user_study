private void collect(SModule current,Set<SModule> result,Deptype depType){
  if (!result.contains(current)) {
    result.add(current);
    for (    SModule m : myUsedModulesCollector.directlyUsedModules(current,myHandler,depType.reexportAll,depType.runtimes)) {
      collect(m,result,depType);
    }
  }
}
