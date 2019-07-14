public PermissionException print(){
  logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
  return this;
}
