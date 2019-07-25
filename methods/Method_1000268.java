@Filters @At @Ok(">>:/user/login") public void logout(HttpServletRequest req){
  HttpSession session=req.getSession(false);
  if (session != null)   session.invalidate();
}
