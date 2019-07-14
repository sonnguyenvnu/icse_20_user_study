@Override public SimpleUserRoleEntity clone(){
  SimpleUserRoleEntity target=new SimpleUserRoleEntity();
  target.setRoleId(getRoleId());
  target.setUserId(getUserId());
  return target;
}
