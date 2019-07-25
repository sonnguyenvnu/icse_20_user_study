private void print(Collection<? extends SModuleReference> modules){
  for (  SModuleReference ref : modules) {
    LOG.info(ref);
  }
}
