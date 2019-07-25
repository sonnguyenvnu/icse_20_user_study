@Modified protected void modified(Map<String,Object> config){
  if (config != null) {
    String configItemName=(String)config.get("item");
    if (configItemName != null && ItemUtil.isValidItemName(configItemName)) {
      itemName=configItemName;
      logger.debug("Using item '{}' for passing voice commands.",itemName);
    }
  }
}
