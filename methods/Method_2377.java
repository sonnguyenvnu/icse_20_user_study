@RequestMapping(value="/signup",method=RequestMethod.GET) public String signup(Model model){
  return thymeleaf("/reg");
}
