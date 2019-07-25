@RequestMapping(value="/save",method=RequestMethod.GET) public String save(long id,String name){
  System.out.println("save ??");
  if (id > 0 && StringUtils.isNotEmpty(name)) {
    Entity newEntity=new Entity(id,name);
    List<Entity> addList=new ArrayList<Entity>();
    addList.add(newEntity);
    cityESService.saveEntity(addList);
    return "OK";
  }
 else {
    return "Bad input value";
  }
}
