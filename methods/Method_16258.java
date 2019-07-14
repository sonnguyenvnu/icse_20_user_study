@PutMapping @ApiOperation("?????????????") public ResponseMessage<Void> saveUserDashBoardConfig(@RequestBody List<String> configIdList,Authentication authentication){
  UserSettingEntity settingEntity=userSettingService.selectByUser(authentication.getUser().getId(),"dashboard-config","current");
  if (settingEntity == null) {
    settingEntity=userSettingService.createEntity();
    settingEntity.setUserId(authentication.getUser().getId());
    settingEntity.setKey("dashboard-config");
    settingEntity.setSettingId("current");
  }
  settingEntity.setSetting(JSON.toJSONString(configIdList));
  userSettingService.saveOrUpdate(settingEntity);
  return ResponseMessage.ok();
}
