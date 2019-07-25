/** 
 * ???????
 */
@RequestMapping({"/admin/init.do","back/init.do"}) @ResponseBody @AuthPassport public JsonResult init(HttpServletRequest request) throws Exception {
  Map<String,String> settingMap=new HashMap<>();
  for (  SettingDto setting : settingCache.getAll()) {
    if (SettingStatus.COMMON.getStatus().equals(setting.getStatus())) {
      settingMap.put(setting.getKey(),setting.getValue());
    }
  }
  Map<String,Object> returnMap=new HashMap<String,Object>();
  returnMap.put("settingMap",settingMap);
  LoginInfoDto user=LoginUserHelper.getUser();
  returnMap.put("sessionAdminName",user.getUserName());
  returnMap.put("adminPermission",user.getAuthStr());
  returnMap.put("sessionAdminId",user.getId());
  returnMap.put("errorTips",stringCache.get(C_CACHE_ERROR_TIP));
  return new JsonResult(1,returnMap);
}
