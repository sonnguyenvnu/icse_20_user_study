@EventListener public void handleUserCreatedEvent(UserCreatedEvent event){
  String key=TotpUtil.getRandomSecretBase32(64);
  UserEntity userEntity=event.getUserEntity();
  String keyUrl=TotpUtil.generateTotpString(userEntity.getUsername(),domain,key);
  userSettingManager.saveSetting(userEntity.getId(),settingId,key,UserSettingPermission.NONE);
  eventPublisher.publishEvent(new TotpTwoFactorCreatedEvent(userEntity,keyUrl));
}
