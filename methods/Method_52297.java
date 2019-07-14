private static PropertyDescriptor<List<String>> propertyFor(String stmtName){
  return PropertyFactory.enumListProperty("check" + StringUtils.capitalize(stmtName) + "LoopTypes",LOOP_TYPES_MAPPINGS).desc("List of loop types in which " + stmtName + " statements will be checked").defaultValue(DEFAULTS).build();
}
