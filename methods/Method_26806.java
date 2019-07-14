@GetMapping("header") public String withHeader(@RequestHeader String Accept){
  return "Obtained 'Accept' header '" + Accept + "'";
}
