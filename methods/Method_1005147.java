public static HqlRuleEnum convert(Object value){
  if (value == null) {
    return null;
  }
  String val=(value + "").toString().trim();
  if (val.length() == 0) {
    return null;
  }
  HqlRuleEnum rule=HqlRuleEnum.getByValue(val.substring(0,1));
  if (rule == null && val.length() >= 2) {
    rule=HqlRuleEnum.getByValue(val.substring(0,2));
  }
  if (rule == null && val.contains(HqlParseEnum.SUFFIX_ASTERISK.getValue())) {
    if (val.startsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue()) && val.endsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue())) {
      rule=HqlRuleEnum.LIKE;
    }
 else     if (val.startsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue())) {
      rule=HqlRuleEnum.LEFT_LIKE;
    }
 else {
      rule=HqlRuleEnum.RIGHT_LIKE;
    }
  }
  if (rule == null && val.contains(HqlParseEnum.SUFFIX_COMMA.getValue())) {
    rule=HqlRuleEnum.IN;
  }
  if (rule == null && val.startsWith(HqlParseEnum.SUFFIX_NOT_EQUAL.getValue())) {
    rule=HqlRuleEnum.NE;
  }
  return rule != null ? rule : HqlRuleEnum.EQ;
}
