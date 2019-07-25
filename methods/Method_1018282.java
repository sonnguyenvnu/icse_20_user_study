@Override public void deploy(){
  for (  Biz biz : bizManagerService.getBizInOrder()) {
    try {
      LOGGER.info(String.format("Begin to start biz: %s",biz.getBizName()));
      biz.start(arguments);
      LOGGER.info(String.format("Finish to start biz: %s",biz.getBizName()));
    }
 catch (    Throwable e) {
      LOGGER.error(String.format("Start biz: %s meet error",biz.getBizName()),e);
      throw new ArkRuntimeException(e);
    }
  }
}
