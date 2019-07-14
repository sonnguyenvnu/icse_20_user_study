@GetMapping("param") public String withParam(@RequestParam String foo){
  return "Obtained 'foo' query parameter value '" + foo + "'";
}
