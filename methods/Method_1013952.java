@Override public ConfigValidationMessage validate(ConfigDescriptionParameter parameter,Object value){
  if (value == null || parameter.getType() != Type.TEXT || parameter.getPattern() == null) {
    return null;
  }
  if (!((String)value).matches(parameter.getPattern())) {
    MessageKey messageKey=MessageKey.PATTERN_VIOLATED;
    return new ConfigValidationMessage(parameter.getName(),messageKey.defaultMessage,messageKey.key,String.valueOf(value),parameter.getPattern());
  }
  return null;
}
