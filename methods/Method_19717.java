@GetMapping("/{id:\\d+}") public void get(@PathVariable String id){
  System.out.println(id);
}
