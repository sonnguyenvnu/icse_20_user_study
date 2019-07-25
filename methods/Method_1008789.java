@Override public User register(User userToAdd){
  final String username=userToAdd.getUsername();
  if (userRepository.findByUsername(username) != null) {
    return null;
  }
  BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
  final String rawPassword=userToAdd.getPassword();
  userToAdd.setPassword(encoder.encode(rawPassword));
  return userRepository.save(userToAdd);
}
