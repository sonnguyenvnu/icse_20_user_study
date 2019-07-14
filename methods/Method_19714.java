@GetMapping("/{id:\\d+}") public void get(@PathVariable String id){
  throw new UserNotExistException(id);
}
