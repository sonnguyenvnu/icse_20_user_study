@RequestMapping(method=RequestMethod.GET) public List<Event> list(HttpServletRequest request){
  return eventService.getByMap(null);
}
