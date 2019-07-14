@ApiOperation(value="????") @RequiresPermissions("cms:article:read") @RequestMapping(value="/list",method=RequestMethod.GET) @ResponseBody public Object list(@RequestParam(required=false,defaultValue="0",value="offset") int offset,@RequestParam(required=false,defaultValue="10",value="limit") int limit,@RequestParam(required=false,value="sort") String sort,@RequestParam(required=false,value="order") String order){
  CmsArticleExample cmsArticleExample=new CmsArticleExample();
  if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
    cmsArticleExample.setOrderByClause(sort + " " + order);
  }
  List<CmsArticle> rows=cmsArticleService.selectByExampleForOffsetPage(cmsArticleExample,offset,limit);
  long total=cmsArticleService.countByExample(cmsArticleExample);
  Map<String,Object> result=new HashMap<>(2);
  result.put("rows",rows);
  result.put("total",total);
  return result;
}
