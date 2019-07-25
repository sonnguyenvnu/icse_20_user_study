public void uninstall(@NotNull IMakeService makeService){
  IMakeService.INSTANCE.set(null);
  myActiveMakeService=null;
}
