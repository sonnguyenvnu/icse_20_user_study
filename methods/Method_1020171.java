@RequestMapping("/index") public String index(ModelMap map){
  map.addAttribute("name","imooc22");
  return "thymeleaf/index";
}
