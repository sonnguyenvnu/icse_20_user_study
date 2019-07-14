public static UserEntity toActivitiUser(org.hswebframework.web.entity.authorization.UserEntity bUser){
  UserEntity userEntity=new UserEntity();
  userEntity.setId(bUser.getId());
  userEntity.setFirstName(bUser.getUsername());
  userEntity.setLastName(bUser.getName());
  userEntity.setPassword(bUser.getPassword());
  userEntity.setRevision(1);
  return userEntity;
}
