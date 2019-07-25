@Override @Transactional(rollbackFor=Exception.class) public void update(User resources){
  Optional<User> userOptional=userRepository.findById(resources.getId());
  ValidationUtil.isNull(userOptional,"User","id",resources.getId());
  User user=userOptional.get();
  User user1=userRepository.findByUsername(user.getUsername());
  User user2=userRepository.findByEmail(user.getEmail());
  if (user1 != null && !user.getId().equals(user1.getId())) {
    throw new EntityExistException(User.class,"username",resources.getUsername());
  }
  if (user2 != null && !user.getId().equals(user2.getId())) {
    throw new EntityExistException(User.class,"email",resources.getEmail());
  }
  if (!resources.getRoles().equals(user.getRoles())) {
    String key="role::loadPermissionByUser:" + user.getUsername();
    redisService.delete(key);
    key="role::findByUsers_Id:" + user.getId();
    redisService.delete(key);
  }
  user.setUsername(resources.getUsername());
  user.setEmail(resources.getEmail());
  user.setEnabled(resources.getEnabled());
  user.setRoles(resources.getRoles());
  user.setDept(resources.getDept());
  user.setJob(resources.getJob());
  user.setPhone(resources.getPhone());
  userRepository.save(user);
}
