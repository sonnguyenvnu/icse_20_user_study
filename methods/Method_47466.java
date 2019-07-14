@Override public UserDetails loadUserByUsername(String username){
  SysUser user=userDao.findByUserName(username);
  if (user == null) {
    throw new UsernameNotFoundException("??????");
  }
  List<SimpleGrantedAuthority> authorities=new ArrayList<>();
  for (  SysRole role : user.getRoles()) {
    authorities.add(new SimpleGrantedAuthority(role.getName()));
    System.out.println(role.getName());
  }
  return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
}
