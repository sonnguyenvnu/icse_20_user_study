public static void setCookie(HttpServletRequest request,HttpServletResponse response,String name,String value,int max_age){
  Cookie c=new Cookie(name,value);
  c.setPath(PATH);
  c.setMaxAge(max_age);
  response.addCookie(c);
}
