@PostMapping("body") public String withBody(@RequestBody String body){
  return "Posted request body '" + body + "'";
}
