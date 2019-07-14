@ApiOperation(value="????") @RequiresPermissions("cms:menu:read") @RequestMapping(value="/list",method=RequestMethod.GET) @ResponseBody public Object list(@RequestParam(required=false,defaultValue="0",value="offset") int offset,@RequestParam(required=false,defaultValue="10",value="limit") int limit,@RequestParam(required=false,value="sort") String sort,@RequestParam(required=false,value="order") String order){
  CmsMenuExample cmsMenuExample=new CmsMenuExample();
  if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
    cmsMenuExample.setOrderByClause(sort + " " + order);
  }
  List<CmsMenu> rows=cmsMenuService.selectByExampleForOffsetPage(cmsMenuExample,offset,limit);
  long total=cmsMenuService.countByExample(cmsMenuExample);
  Map<String,Object> result=new HashMap<>(2);
  result.put("rows",rows);
  result.put("total",total);
  return result;
}
