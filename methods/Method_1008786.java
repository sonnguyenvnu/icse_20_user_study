@RequestMapping(value="/search",method=RequestMethod.GET) public List<Entity> save(String name){
  List<Entity> entityList=null;
  if (StringUtils.isNotEmpty(name)) {
    entityList=cityESService.searchEntity(name);
  }
  return entityList;
}
