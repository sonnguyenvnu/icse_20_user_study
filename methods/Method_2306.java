@ApiOperation(value="????") @RequiresPermissions("cms:page:read") @RequestMapping(value="/list",method=RequestMethod.GET) @ResponseBody public Object list(@RequestParam(required=false,defaultValue="0",value="offset") int offset,@RequestParam(required=false,defaultValue="10",value="limit") int limit,@RequestParam(required=false,value="sort") String sort,@RequestParam(required=false,value="order") String order){
  CmsPageExample cmsPageExample=new CmsPageExample();
  if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
    cmsPageExample.setOrderByClause(sort + " " + order);
  }
  List<CmsPage> rows=cmsPageService.selectByExampleForOffsetPage(cmsPageExample,offset,limit);
  long total=cmsPageService.countByExample(cmsPageExample);
  Map<String,Object> result=new HashMap<>(2);
  result.put("rows",rows);
  result.put("total",total);
  return result;
}
