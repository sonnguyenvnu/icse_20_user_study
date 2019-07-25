@Override public void logout(HttpServletRequest request,HttpServletResponse response){
  try {
    response.sendRedirect("/");
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
