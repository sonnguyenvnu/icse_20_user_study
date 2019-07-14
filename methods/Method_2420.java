@ApiOperation(value="??????") @RequiresPermissions("upms:permission:read") @RequestMapping(value="/user/{id}",method=RequestMethod.POST) @ResponseBody public Object user(@PathVariable("id") int id,HttpServletRequest request){
  return upmsPermissionService.getTreeByUserId(id,NumberUtils.toByte(request.getParameter("type")));
}
