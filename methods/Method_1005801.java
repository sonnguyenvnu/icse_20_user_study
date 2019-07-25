@RequestMapping("/setting/detail.do") @ResponseBody @AuthPassport(authority=C_AUTH_SETTING) public JsonResult detail(String id,String key,String type){
  Setting setting=null;
  if (id != null) {
    setting=settingService.getById(id);
  }
 else   if (key != null) {
    setting=customSettingService.getByKey(key);
  }
  if (setting == null) {
    setting=new Setting();
    setting.setType(type);
  }
  return new JsonResult().data(SettingAdapter.getDto(setting));
}
