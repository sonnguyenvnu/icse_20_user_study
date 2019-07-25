@Override public boolean insert(UserEntity userEntity){
  String username=userEntity.getUsername();
  if (exist(username)) {
    return false;
  }
  encryptPassword(userEntity);
  userEntity.setRoles(RoleConstant.ROLE_USER);
  int result=userMapper.insert(userEntity);
  return result == 1;
}
