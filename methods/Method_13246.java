@Override public boolean canLoad(String internalName){
  return this.getClass().getResource("/" + internalName + ".class") != null;
}
