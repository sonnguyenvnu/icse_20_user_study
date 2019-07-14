@RequestMapping(value="/{id}",method=RequestMethod.GET) public Event detail(@PathVariable Integer id){
  return eventService.getById(id);
}
