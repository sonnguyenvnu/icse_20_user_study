@Override public void run(){
  try {
    if (consoleService != null) {
      DcMeta future=consoleService.getDcMeta(currentDc);
      DcMeta current=dcMetaManager.get().getDcMeta();
      changeDcMeta(current,future);
      checkRouteChange(current,future);
    }
  }
 catch (  Throwable th) {
    logger.error("[run]" + th.getMessage());
  }
}
