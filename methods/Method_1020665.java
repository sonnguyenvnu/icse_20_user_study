/** 
 * ??????????
 */
@RequestMapping("/json") @ResponseBody public R<List<Map<String,Object>>> json(String parentId){
  QueryWrapper<SysMenu> ew=new QueryWrapper<SysMenu>();
  ew.orderByAsc("sort");
  ew.eq("parent_id",parentId);
  List<SysMenu> list=sysMenuService.list(ew);
  List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
  for (  SysMenu m : list) {
    Map<String,Object> map=Maps.newHashMap();
    map.put("id",m.getId());
    map.put("text",StringUtils.join(m.getCode(),"-",m.getMenuName()));
    listMap.add(map);
  }
  return new R<List<Map<String,Object>>>(listMap);
}
