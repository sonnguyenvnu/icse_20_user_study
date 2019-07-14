@GetMapping("/form") public MultiValueMap<String,String> writeForm(){
  MultiValueMap<String,String> map=new LinkedMultiValueMap<String,String>();
  map.add("foo","bar");
  map.add("fruit","apple");
  return map;
}
