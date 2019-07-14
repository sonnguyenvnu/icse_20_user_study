@GetMapping(path="/mapping/produces",produces=MediaType.APPLICATION_XML_VALUE) public JavaBean byProducesXml(){
  return new JavaBean();
}
