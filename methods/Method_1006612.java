private void validate(ConfigType configType,ConfigItem configItem){
  if (null == configType || null == configItem) {
    throw new IllegalArgumentException(String.format("ConfigType {%s}, ConfigItem {%s} should not be null!",configType,configItem));
  }
}
