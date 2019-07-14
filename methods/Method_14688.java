public static void deleteCookie(HttpServletRequest request,HttpServletResponse response,String name){
  Cookie c=new Cookie(name,"");
  c.setPath(PATH);
  c.setMaxAge(0);
  response.addCookie(c);
}
