@Override public UserDetails loadUserByUsername(String userName){
  User user=userDao.getByUserName(userName);
  if (user != null) {
    List<Permission> permissions=permissionDao.getByUserId(user.getId());
    List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
    for (    Permission permission : permissions) {
      if (permission != null && permission.getName() != null) {
        GrantedAuthority grantedAuthority=new UrlGrantedAuthority(permission.getPermissionUrl(),permission.getMethod());
        grantedAuthorities.add(grantedAuthority);
      }
    }
    user.setGrantedAuthorities(grantedAuthorities);
    return user;
  }
 else {
    throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
  }
}
