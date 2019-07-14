@RequestMapping("/index") public String index(Model model){
  User user=(User)SecurityUtils.getSubject().getPrincipal();
  model.addAttribute("user",user);
  return "index";
}
