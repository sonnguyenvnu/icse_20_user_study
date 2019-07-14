@Override public void create(User user){
  Optional<User> existing=repository.findById(user.getUsername());
  existing.ifPresent(it -> {
    throw new IllegalArgumentException("user already exists: " + it.getUsername());
  }
);
  String hash=encoder.encode(user.getPassword());
  user.setPassword(hash);
  repository.save(user);
  log.info("new user has been created: {}",user.getUsername());
}
