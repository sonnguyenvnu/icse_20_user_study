@GetMapping("/rest/trace/{name}") public String trace(@PathVariable("name") String name){
  return "hello:" + name + " this is the mps trace service";
}
