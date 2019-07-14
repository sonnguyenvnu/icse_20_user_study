@Override public UserDetails loadUserByUsername(String username){
  SysUser user=userDao.findByUserName(username);
  if (user == null) {
    throw new UsernameNotFoundException("??????");
  }
  List<SimpleGrantedAuthority> authorities=new ArrayList<>();
  for (  SysRole role : user.getRoles()) {
    authorities.add(new SimpleGrantedAuthority(role.getName()));
    logger.info("loadUserByUsername: " + user);
  }
  user.setGrantedAuthorities(authorities);
  return user;
}
