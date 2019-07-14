public static String formatPluralString(String key,int plural){
  if (key == null || key.length() == 0 || getInstance().currentPluralRules == null) {
    return "LOC_ERR:" + key;
  }
  String param=getInstance().stringForQuantity(getInstance().currentPluralRules.quantityForNumber(plural));
  param=key + "_" + param;
  int resourceId=ApplicationLoader.applicationContext.getResources().getIdentifier(param,"string",ApplicationLoader.applicationContext.getPackageName());
  return formatString(param,resourceId,plural);
}
