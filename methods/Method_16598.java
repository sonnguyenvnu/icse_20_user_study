protected void syncUserInfo(PersonAuthBindEntity bindEntity){
  if (isEmpty(bindEntity.getPersonUser().getUsername())) {
    bindEntity.setUserId("");
    return;
  }
  if (null == userService) {
    logger.warn("userService not ready!");
    return;
  }
  UserEntity oldUser=userService.selectByUsername(bindEntity.getPersonUser().getUsername());
  if (null != oldUser) {
    int userBindSize=createQuery().where().is(PersonEntity.userId,oldUser.getId()).not(PersonEntity.id,bindEntity.getId()).total();
    tryValidateProperty(userBindSize == 0,"personUser.username","?????????");
  }
  Function<UserEntity,String> userOperationFunction=oldUser == null ? userService::insert : user -> {
    userService.update(oldUser.getId(),user);
    return oldUser.getId();
  }
;
  UserEntity userEntity=entityFactory.newInstance(UserEntity.class);
  userEntity.setUsername(bindEntity.getPersonUser().getUsername());
  userEntity.setPassword(bindEntity.getPersonUser().getPassword());
  userEntity.setName(bindEntity.getName());
  String userId=userOperationFunction.apply(userEntity);
  bindEntity.setUserId(userId);
}
