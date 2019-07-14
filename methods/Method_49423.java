private static boolean hasDualStringMapping(KeyInformation information){
  return AttributeUtil.isString(information.getDataType()) && getStringMapping(information) == Mapping.TEXTSTRING;
}
