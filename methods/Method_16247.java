@GetMapping("/{type}/{settingFor}") @Authorize(action=Permission.ACTION_GET) @ApiOperation("??type?settingFor????") public ResponseMessage<AuthorizationSettingEntity> select(@PathVariable String type,@PathVariable String settingFor){
  return ResponseMessage.ok(authorizationSettingService.select(type,settingFor));
}
