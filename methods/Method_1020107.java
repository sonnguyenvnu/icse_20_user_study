@RequestMapping("/demo2") public ModelAndView main(HttpServletRequest request){
  ModelAndView mv=new ModelAndView();
  if (LoginCheck.checkCookie(request)) {
    mv.setViewName("demo2");
    return mv;
  }
  mv.addObject("gotoUrl","/demo2");
  mv.setViewName("login");
  return mv;
}
