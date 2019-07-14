public UserDetails loadUserByUsername(String username){
  SysUser user=userDao.findByUserName(username);
  if (user != null) {
    List<Permission> permissions=permissionDao.findByAdminUserId(user.getId());
    List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
    for (    Permission permission : permissions) {
      if (permission != null && permission.getName() != null) {
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(permission.getName());
        grantedAuthorities.add(grantedAuthority);
      }
    }
    return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
  }
 else {
    throw new UsernameNotFoundException("admin: " + username + " do not exist!");
  }
}
