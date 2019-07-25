@RequestMapping("/demo2") public ModelAndView main(HttpServletRequest request){
  ModelAndView mv=new ModelAndView();
  Cookie[] cookies=request.getCookies();
  if (cookies != null && cookies.length > 0) {
    for (    Cookie cookie : cookies) {
      if ("ssocookie".equals(cookie.getName())) {
        String url="http://check.x.com:8080/sso/checkCookie";
        RespMessage respMessage=HttpUtils.doGet(url,cookie.getName(),cookie.getValue());
        if ("200".equals(respMessage.getRespCode())) {
          mv.setViewName("demo2");
          return mv;
        }
      }
    }
  }
  mv.addObject("gotoUrl","http://demo2.x.com:8080/demo2");
  mv.setViewName("login");
  return mv;
}
