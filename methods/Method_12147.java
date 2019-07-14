@Override public void register(String username,String password){
  int count=loginInfoMapper.getCountByNickname(username,Constants.USERTYPE_NORMAL);
  if (count <= 0) {
    Logininfo logininfo=new Logininfo();
    logininfo.setNickname(username);
    String salt=MD5Utils.getSaltT();
    logininfo.setPassword(MD5Utils.formPassToDBPass(password,salt));
    logininfo.setState(Constants.STATE_NORMAL);
    logininfo.setUserType(Constants.USERTYPE_NORMAL);
    logininfo.setRegisterDate(new Date());
    logininfo.setLastLoginDate(new Date());
    logininfo.setSalt(salt);
    this.loginInfoMapper.insert(logininfo);
    Account account=Account.empty(logininfo.getId());
    accountMapper.insert(account);
    Userinfo userinfo=Userinfo.empty(logininfo.getId());
    int result=this.userinfoMapper.insert(userinfo);
  }
 else {
    throw new RuntimeException("???????!");
  }
}
