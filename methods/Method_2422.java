@ApiOperation(value="????") @RequiresPermissions("upms:system:read") @RequestMapping(value="/list",method=RequestMethod.GET) @ResponseBody public Object list(@RequestParam(required=false,defaultValue="0",value="offset") int offset,@RequestParam(required=false,defaultValue="10",value="limit") int limit,@RequestParam(required=false,defaultValue="",value="search") String search,@RequestParam(required=false,value="sort") String sort,@RequestParam(required=false,value="order") String order){
  UpmsSystemExample upmsSystemExample=new UpmsSystemExample();
  if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
    upmsSystemExample.setOrderByClause(sort + " " + order);
  }
  if (StringUtils.isNotBlank(search)) {
    upmsSystemExample.or().andTitleLike("%" + search + "%");
  }
  List<UpmsSystem> rows=upmsSystemService.selectByExampleForOffsetPage(upmsSystemExample,offset,limit);
  long total=upmsSystemService.countByExample(upmsSystemExample);
  Map<String,Object> result=new HashMap<>();
  result.put("rows",rows);
  result.put("total",total);
  return result;
}
