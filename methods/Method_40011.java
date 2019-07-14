@Override public String process() throws Throwable {
  String bizName=nameCommandOption.getArgs()[0];
  String bizVersion=versionCommandOption.getArgs()[0];
  Biz biz=bizManagerService.getBiz(bizName,bizVersion);
  if (biz != null) {
    if (biz.getBizState() != BizState.ACTIVATED && biz.getBizState() != BizState.DEACTIVATED) {
      LOGGER.info(String.format("The specified biz's state must not be %s.",biz.getBizState()));
      return String.format("The specified biz's state must not be %s.",biz.getBizState());
    }
 else {
      bizManagerService.activeBiz(bizName,bizVersion);
      LOGGER.info(String.format("Switch biz:\'%s\' to be activated.",biz.getIdentity()));
      return String.format("Biz:\'%s\' is activated.",biz.getIdentity());
    }
  }
 else {
    LOGGER.info("The specified biz does not exists.");
    return "The specified biz does not exists.";
  }
}
