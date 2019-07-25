@RequestMapping(method=RequestMethod.GET) public ResponseEntity<?> list(){
  final List<EventType> eventTypes=eventTypeService.list();
  return status(HttpStatus.OK).body(eventTypes);
}
