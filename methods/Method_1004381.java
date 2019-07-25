@Override public void run(){
  final DoPullParam doPullParam=new DoPullParam();
  while (isRunning.get()) {
    try {
      if (!preparePull()) {
        LOGGER.debug(logType,"preparePull false. subject={}, group={}",pushConsumer.subject(),pushConsumer.group());
        continue;
      }
      if (!resetDoPullParam(doPullParam)) {
        LOGGER.debug(logType,"buildDoPullParam false. subject={}, group={}",pushConsumer.subject(),pushConsumer.group());
        continue;
      }
      if (isRunning.get() && onlineSwitcher.waitOn()) {
        doPull(doPullParam);
      }
    }
 catch (    Exception e) {
      LOGGER.error("PullEntry run exception",e);
    }
  }
}
