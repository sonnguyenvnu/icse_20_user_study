@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  MyUser user=new MyUser();
  user.setUserName(username);
  user.setPassword(this.passwordEncoder.encode("123456"));
  System.out.println(user.getPassword());
  return new User(username,user.getPassword(),user.isEnabled(),user.isAccountNonExpired(),user.isCredentialsNonExpired(),user.isAccountNonLocked(),AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
}
