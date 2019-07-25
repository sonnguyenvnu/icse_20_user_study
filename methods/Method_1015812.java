@RequestMapping(path="/ping",method=RequestMethod.GET) public Map<String,String> ping(){
  Map<String,String> pong=new HashMap<>();
  pong.put("pong","Hello, World!");
  return pong;
}
