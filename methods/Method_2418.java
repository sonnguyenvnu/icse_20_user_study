@ApiOperation(value="????") @RequiresPermissions("upms:permission:read") @RequestMapping(value="/list",method=RequestMethod.GET) @ResponseBody public Object list(@RequestParam(required=false,defaultValue="0",value="offset") int offset,@RequestParam(required=false,defaultValue="10",value="limit") int limit,@RequestParam(required=false,defaultValue="",value="search") String search,@RequestParam(required=false,defaultValue="0",value="type") int type,@RequestParam(required=false,defaultValue="0",value="systemId") int systemId,@RequestParam(required=false,value="sort") String sort,@RequestParam(required=false,value="order") String order){
  UpmsPermissionExample upmsPermissionExample=new UpmsPermissionExample();
  UpmsPermissionExample.Criteria criteria=upmsPermissionExample.createCriteria();
  if (0 != type) {
    criteria.andTypeEqualTo((byte)type);
  }
  if (0 != systemId) {
    criteria.andSystemIdEqualTo(systemId);
  }
  if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
    upmsPermissionExample.setOrderByClause(sort + " " + order);
  }
  if (StringUtils.isNotBlank(search)) {
    upmsPermissionExample.or().andNameLike("%" + search + "%");
  }
  List<UpmsPermission> rows=upmsPermissionService.selectByExampleForOffsetPage(upmsPermissionExample,offset,limit);
  long total=upmsPermissionService.countByExample(upmsPermissionExample);
  Map<String,Object> result=new HashMap<>();
  result.put("rows",rows);
  result.put("total",total);
  return result;
}
