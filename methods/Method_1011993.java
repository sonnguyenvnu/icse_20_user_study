@Override public Iterator<SModuleReference> iterator(){
  List<SModuleReference> refs=new ModelAccessHelper(myRepo).runReadAction((Computable<List<SModuleReference>>)() -> {
    ArrayList<SModuleReference> rv=new ArrayList<>(100);
    for (    SModule m : myScope.getModules()) {
      rv.add(m.getModuleReference());
    }
    return rv;
  }
);
  return refs.iterator();
}
