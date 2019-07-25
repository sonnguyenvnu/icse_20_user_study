@Override @Transactional public void update(RegisterCenterModel model){
  RegisterCenter registerCenter=registerCenterMapper.selectById(model.getId());
  if (null == registerCenter) {
    return;
  }
  registerCenter.setGmtModified(new Timestamp(System.currentTimeMillis()));
  registerCenterMapper.updateById(BeanMapper.map(model,RegisterCenter.class));
  if (Constants.REGISTER_CENTER_ENABLE == model.getStatus()) {
    discoveryRegistryManager.addRegistry(model.getCode(),model.getUrl());
  }
 else {
    discoveryRegistryManager.removeRegistry(registerCenter.getCode());
  }
}
