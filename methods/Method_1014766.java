@GetMapping("/") String uid(HttpSession session){
  if (session.getAttribute(SESSION_KEY) == null)   return "not logged in";
  User user=(User)session.getAttribute(SESSION_KEY);
  return session.getId() + "," + JSON.toJSONString(user);
}
