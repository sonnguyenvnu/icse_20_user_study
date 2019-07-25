@Override @Transactional(rollbackFor=Exception.class) public UserDTO create(User resources){
  if (userRepository.findByUsername(resources.getUsername()) != null) {
    throw new EntityExistException(User.class,"username",resources.getUsername());
  }
  if (userRepository.findByEmail(resources.getEmail()) != null) {
    throw new EntityExistException(User.class,"email",resources.getEmail());
  }
  resources.setPassword("e10adc3949ba59abbe56e057f20f883e");
  resources.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
  return userMapper.toDto(userRepository.save(resources));
}
