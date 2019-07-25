@Override public boolean submit(User user){
  if (Func.isNotEmpty(user.getPassword())) {
    user.setPassword(DigestUtil.encrypt(user.getPassword()));
  }
  Integer cnt=baseMapper.selectCount(Wrappers.<User>query().lambda().eq(User::getTenantCode,user.getTenantCode()).eq(User::getAccount,user.getAccount()));
  if (cnt > 0) {
    throw new ApiException("???????!");
  }
  return saveOrUpdate(user);
}
