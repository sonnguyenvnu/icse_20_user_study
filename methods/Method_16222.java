@Override @Transactional(readOnly=true) public UserEntity selectByUsername(String username){
  if (!StringUtils.hasLength(username)) {
    return null;
  }
  return createQuery().where("username",username).single();
}
