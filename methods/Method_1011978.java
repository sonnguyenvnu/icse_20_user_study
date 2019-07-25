@Override public boolean met(SModule m){
  for (  Class<? extends SModule> c : myModuleClasses) {
    if (c.isInstance(m)) {
      return true;
    }
  }
  return false;
}
