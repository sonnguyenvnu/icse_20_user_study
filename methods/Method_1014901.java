@RequestMapping("/entity") public ModelAndView index(){
  ModelAndView mv=new ModelAndView();
  int sum=12 + 13;
  UserInfo u=service.getUser();
  Map<String,UserInfo> hu=service.allUser();
  Collection<UserInfo> cu=service.allUser().values();
  List<UserInfo> lu=new ArrayList<>(cu);
  mv.addObject("sum",sum);
  mv.addObject("u",u);
  mv.addObject("hu",hu);
  mv.addObject("lu",lu);
  return mv;
}
