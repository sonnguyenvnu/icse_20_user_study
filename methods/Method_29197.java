private Boolean updateInstanceByRun(boolean isRun,InstanceInfo info){
  if (isRun) {
    if (info.getStatus() != InstanceStatusEnum.GOOD_STATUS.getStatus()) {
      info.setStatus(InstanceStatusEnum.GOOD_STATUS.getStatus());
      instanceDao.update(info);
      logger.warn("instance:{} instance is run",info);
      saveFault(info,isRun);
      return true;
    }
  }
 else {
    if (info.getStatus() != InstanceStatusEnum.ERROR_STATUS.getStatus()) {
      info.setStatus(InstanceStatusEnum.ERROR_STATUS.getStatus());
      instanceDao.update(info);
      logger.error("instance:{} instance failed",info);
      saveFault(info,isRun);
      return false;
    }
  }
  return null;
}
