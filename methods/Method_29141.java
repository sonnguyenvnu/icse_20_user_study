public boolean isSpecail(){
  return instanceId > 0 && type == InstanceAlertTypeEnum.INSTANCE_ALERT.getValue();
}
