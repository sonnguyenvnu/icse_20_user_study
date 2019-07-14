@PostMapping(path="/consumes",consumes="application/json") public String byConsumes(@RequestBody JavaBean javaBean){
  return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
}
