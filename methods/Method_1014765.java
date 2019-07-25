@Override public void run(String... args) throws Exception {
  User user=User.builder().username("javahongxi").nickname("hongxi").gender(1).age(29).build();
  User u=userMapper.findByUsername(user.getUsername());
  if (u != null) {
    log.info("user.user exists, {}",u);
  }
 else {
    userMapper.insert(user);
  }
  u=orderMapper.findByUsername(user.getUsername());
  if (u != null) {
    log.info("trade.user exists, {}",u);
  }
 else {
    orderMapper.insert(user);
  }
}
