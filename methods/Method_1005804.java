@RequestMapping("/oschina/login.ignore") public String login(@RequestParam String code,HttpServletRequest request,HttpServletResponse response) throws Exception {
  User user=null;
  GitHubUser oschinaUser=oschinaService.getUser(oschinaService.getAccessToken(code,settingCache.getDomain()).getAccess_token());
  List<User> users=userService.query(new UserQuery().setThirdlyId(getThirdlyId(oschinaUser)));
  if (users.size() == 0) {
    user=new User();
    user.setUserName(Tools.handleUserName(oschinaUser.getLogin()));
    user.setTrueName(oschinaUser.getName());
    if (!MyString.isEmpty(oschinaUser.getEmail())) {
      List<User> existUser=userService.query(new UserQuery().setEqualEmail(oschinaUser.getEmail()).setLoginType(LoginType.GITHUB.getValue()));
      if (existUser == null || existUser.size() == 0) {
        user.setEmail(oschinaUser.getEmail());
      }
    }
    user.setPassword("");
    user.setStatus(UserStatus.INVALID.getType());
    user.setType(UserType.USER.getType());
    String avatarUrl=oschinaUser.getAvatar_url();
    if (avatarUrl.contains("?")) {
      avatarUrl=avatarUrl.substring(0,avatarUrl.indexOf("?"));
    }
    user.setAvatarUrl(avatarUrl);
    user.setThirdlyId(getThirdlyId(oschinaUser));
    user.setLoginType(LoginType.OSCHINA.getValue());
    userService.insert(user);
  }
 else {
    user=users.get(0);
  }
  LoginDto model=new LoginDto();
  model.setUserName(user.getUserName());
  model.setRemberPwd("NO");
  ThreadContext.set(request,response);
  try {
    customUserService.login(model,user);
  }
 catch (  Exception e) {
    throw e;
  }
 finally {
    ThreadContext.clear();
  }
  response.sendRedirect("../admin.do");
  return null;
}
