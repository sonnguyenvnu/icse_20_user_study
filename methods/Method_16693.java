@Override public UserEntity findUserById(final String userId){
  if (userId == null) {
    return null;
  }
  org.hswebframework.web.entity.authorization.UserEntity user=userService.selectByPk(userId);
  return ActivitiUserUtil.toActivitiUser(user);
}
