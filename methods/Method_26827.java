@PostMapping(path="/mapping/consumes",consumes=MediaType.APPLICATION_JSON_VALUE) public String byConsumes(@RequestBody JavaBean javaBean){
  return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
}
