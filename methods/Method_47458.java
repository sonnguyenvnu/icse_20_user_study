@RequestMapping(value="/{id}",method=RequestMethod.GET) public User detail(@PathVariable Integer id){
  return userService.getById(id);
}
