@RequestMapping("/demo2") public ModelAndView main(HttpServletRequest request){
  ModelAndView mv=new ModelAndView();
  Cookie[] cookies=request.getCookies();
  if (cookies != null && cookies.length > 0) {
    for (    Cookie cookie : cookies) {
      if ("ssocookie".equals(cookie.getName())) {
        Map<String,String> param=new HashMap<String,String>(16);
        param.put("cookieName",cookie.getName());
        param.put("cookieValue",cookie.getValue());
        String url="http://www.x.com/sso/checkCookie";
        RespMessage respMessage=HttpUtils.doGet(url,param);
        if ("200".equals(respMessage.getRespCode())) {
          mv.setViewName("demo2");
          return mv;
        }
      }
    }
  }
  mv.addObject("contextPath",request.getContextPath());
  mv.addObject("path","b");
  mv.addObject("gotoUrl","http://www.b.com/b/demo2");
  mv.setViewName("login");
  return mv;
}
