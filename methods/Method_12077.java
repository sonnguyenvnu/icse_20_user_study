public static Logininfo getCurrent(){
  return (Logininfo)getRequest().getSession().getAttribute(LOGIN_IN_SESSION);
}
