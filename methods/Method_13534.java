@Override @GetMapping(value="/param") public String param(@RequestParam String param){
  log("/param",param);
  return param;
}
