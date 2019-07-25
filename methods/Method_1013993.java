@Modified public void modified(@Nullable Map<String,Object> properties){
  if (properties != null) {
    String enabled=(String)properties.get("singleThread");
    manager.setEnforceSingleThreadPerIdentifier("true".equalsIgnoreCase(enabled));
  }
}
