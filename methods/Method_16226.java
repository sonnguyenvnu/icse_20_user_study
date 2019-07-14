@Override @CacheEvict(value=CacheConstants.USER_CACHE_NAME,key="#userEntity.id") public String insert(UserEntity userEntity){
  tryValidateProperty(usernameValidator,UserEntity.username,userEntity.getUsername());
  tryValidateProperty(null == selectByUsername(userEntity.getUsername()),UserEntity.username,"??????");
  tryValidateProperty(passwordStrengthValidator,UserEntity.password,userEntity.getPassword());
  userEntity.setCreateTime(System.currentTimeMillis());
  userEntity.setId(getIdGenerator().generate());
  userEntity.setSalt(IDGenerator.RANDOM.generate());
  userEntity.setStatus(DataStatus.STATUS_ENABLED);
  tryValidate(userEntity,CreateGroup.class);
  userEntity.setPassword(encodePassword(userEntity.getPassword(),userEntity.getSalt()));
  userDao.insert(userEntity);
  if (userEntity instanceof BindRoleUserEntity) {
    BindRoleUserEntity bindRoleUserEntity=((BindRoleUserEntity)userEntity);
    if (!ListUtils.isNullOrEmpty(bindRoleUserEntity.getRoles())) {
      trySyncUserRole(userEntity.getId(),bindRoleUserEntity.getRoles());
    }
  }
  publisher.publishEvent(new UserCreatedEvent(userEntity));
  return userEntity.getId();
}
