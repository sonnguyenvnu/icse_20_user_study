@Override public void set(ConfigType configType,ConfigItem configItem,Object value){
  validate(configType,configItem,value);
  Map<ConfigItem,Object> items=userConfigs.get(configType);
  if (null == items) {
    items=new HashMap<ConfigItem,Object>();
    userConfigs.put(configType,items);
  }
  Object prev=items.put(configItem,value);
  if (null != prev) {
    logger.warn("the value of ConfigType {}, ConfigItem {} changed from {} to {}",configType,configItem,prev.toString(),value.toString());
  }
}
