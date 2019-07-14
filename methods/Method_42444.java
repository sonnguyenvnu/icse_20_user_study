public SettBizException print(){
  LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
  return this;
}
