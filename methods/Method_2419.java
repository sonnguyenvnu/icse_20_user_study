@ApiOperation(value="??????") @RequiresPermissions("upms:permission:read") @RequestMapping(value="/role/{id}",method=RequestMethod.POST) @ResponseBody public Object role(@PathVariable("id") int id){
  return upmsPermissionService.getTreeByRoleId(id);
}
