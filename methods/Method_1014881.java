@RequestMapping(value={"/add/{id}"},method=RequestMethod.GET) public Object add(@PathVariable("id") long id){
  UserEntity user=new UserEntity(id,id + "",Integer.valueOf(id + ""),new Date());
  List<UserEntity> list=new ArrayList<>();
  list=service.addUser(user);
  Collections.sort(list);
  return list;
}
