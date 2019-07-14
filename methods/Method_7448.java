String format(String orig){
  String str=orig;
  String trunkPrefix=null;
  String intlPrefix=null;
  if (str.startsWith(callingCode)) {
    intlPrefix=callingCode;
    str=str.substring(intlPrefix.length());
  }
 else {
    String trunk=matchingTrunkCode(str);
    if (trunk != null) {
      trunkPrefix=trunk;
      str=str.substring(trunkPrefix.length());
    }
  }
  for (  RuleSet set : ruleSets) {
    String phone=set.format(str,intlPrefix,trunkPrefix,true);
    if (phone != null) {
      return phone;
    }
  }
  for (  RuleSet set : ruleSets) {
    String phone=set.format(str,intlPrefix,trunkPrefix,false);
    if (phone != null) {
      return phone;
    }
  }
  if (intlPrefix != null && str.length() != 0) {
    return String.format("%s %s",intlPrefix,str);
  }
  return orig;
}
