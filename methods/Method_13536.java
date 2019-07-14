@Override @GetMapping("/path-variables/{p1}/{p2}") public String pathVariables(@PathVariable("p1") String path1,@PathVariable("p2") String path2,@RequestParam("v") String param){
  String result=path1 + " , " + path2 + " , " + param;
  log("/path-variables",result);
  return result;
}
