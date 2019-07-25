@Override public boolean contains(ConfigType configType,ConfigItem configItem){
  validate(configType,configItem);
  return null != userConfigs.get(configType) && userConfigs.get(configType).containsKey(configItem);
}
