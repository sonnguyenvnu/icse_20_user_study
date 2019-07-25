private void validate(ConfigType configType,ConfigItem configItem,Object value){
  if (null == configType || null == configItem || null == value) {
    throw new IllegalArgumentException(String.format("ConfigType {%s}, ConfigItem {%s}, value {%s} should not be null!",configType,configItem,value == null ? null : value.toString()));
  }
}
