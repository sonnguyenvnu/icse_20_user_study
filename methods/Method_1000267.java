@Filters @POST @At public NutMap login(String username,String password,HttpSession session){
  NutMap re=new NutMap("ok",false);
  if (Strings.isBlank(username) || Strings.isBlank(password)) {
    log.debug("username or password is null");
    return re.setv("msg","??????????");
  }
  User user=dao.fetch(User.class,username);
  if (user == null) {
    log.debug("no such user = " + username);
    return re.setv("msg","?????");
  }
  String tmp=Lang.digest("SHA-256",user.getSalt() + password);
  if (!tmp.equals(user.getPassword())) {
    log.debug("password is wrong");
    return re.setv("msg","????");
  }
  session.setAttribute("me",user);
  return re.setv("ok",true);
}
