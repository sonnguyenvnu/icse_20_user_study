private static char delimiterIn(Map<PropertyDescriptorField,String> valuesById,char defalt){
  String characterStr="";
  if (valuesById.containsKey(DELIMITER)) {
    characterStr=valuesById.get(DELIMITER).trim();
  }
  if (StringUtils.isBlank(characterStr)) {
    return defalt;
  }
  if (characterStr.length() != 1) {
    throw new RuntimeException("Ambiguous delimiter character, must have length 1: \"" + characterStr + "\"");
  }
  return characterStr.charAt(0);
}
