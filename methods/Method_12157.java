public static void putLogininfo(Logininfo logininfo){
  HttpServletRequest a=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
  HttpSession b=a.getSession();
  b.setAttribute(LOGIN_IN_SESSION,logininfo);
}
