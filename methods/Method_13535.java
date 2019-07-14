@Override @GetMapping("/headers") public String headers(@RequestHeader("h") String header,@RequestHeader("h2") String header2,@RequestParam("v") Integer param){
  String result=header + " , " + header2 + " , " + param;
  log("/headers",result);
  return result;
}
