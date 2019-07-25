@Override public List<SModuleReference> compute(){
  ArrayList<SModuleReference> rv=new ArrayList<>(50);
  for (  SModule m : myModules) {
    rv.add(m.getModuleReference());
  }
  return rv;
}
