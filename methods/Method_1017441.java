public MenuMatchRule platform(ClientPlatformType platformType){
  if (platformType != null) {
    matchRule.put("client_platform_type",platformType.ordinal() + 1);
  }
  this.platformType=platformType;
  return this;
}
