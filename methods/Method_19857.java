@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  MyUser user=new MyUser();
  user.setUserName(username);
  user.setPassword(this.passwordEncoder.encode("123456"));
  System.out.println(user.getPassword());
  List<GrantedAuthority> authorities=new ArrayList<>();
  if (StringUtils.equalsIgnoreCase("mrbird",username)) {
    authorities=AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
  }
 else {
    authorities=AuthorityUtils.commaSeparatedStringToAuthorityList("test");
  }
  return new User(username,user.getPassword(),user.isEnabled(),user.isAccountNonExpired(),user.isCredentialsNonExpired(),user.isAccountNonLocked(),authorities);
}
