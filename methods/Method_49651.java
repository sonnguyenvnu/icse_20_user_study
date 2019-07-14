@Override public boolean supports(KeyInformation information){
  final Class<?> dataType=information.getDataType();
  final Mapping mapping=Mapping.getMapping(information);
  if (Number.class.isAssignableFrom(dataType) || dataType == Date.class || dataType == Instant.class || dataType == Boolean.class || dataType == UUID.class) {
    return mapping == Mapping.DEFAULT;
  }
 else   if (AttributeUtil.isString(dataType)) {
    return mapping == Mapping.DEFAULT || mapping == Mapping.TEXT || mapping == Mapping.STRING;
  }
 else   if (AttributeUtil.isGeo(dataType)) {
    return mapping == Mapping.DEFAULT || mapping == Mapping.PREFIX_TREE;
  }
  return false;
}
