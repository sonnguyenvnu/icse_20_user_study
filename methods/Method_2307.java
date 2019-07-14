@ApiOperation(value="????") @RequiresPermissions("cms:setting:update") @RequestMapping(value="/update/{id}",method=RequestMethod.GET) public String update(@PathVariable("id") int id,ModelMap modelMap){
  CmsSetting setting=cmsSettingService.selectByPrimaryKey(id);
  modelMap.put("setting",setting);
  return "/manage/setting/update.jsp";
}
