@GetMapping(path="/mapping/produces",produces=MediaType.APPLICATION_JSON_VALUE) public JavaBean byProducesJson(){
  return new JavaBean();
}
