@RequestMapping("hello") public String hello(ModelMap map){
  map.put("host","??freemarker!");
  return "index";
}
