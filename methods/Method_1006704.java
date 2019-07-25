public void authenticate(Customer customer,String userName,String password) throws Exception {
  Validate.notNull(customer,"Customer cannot be null");
  Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
  GrantedAuthority role=new SimpleGrantedAuthority(ROLE_PREFIX + Constants.PERMISSION_CUSTOMER_AUTHENTICATED);
  authorities.add(role);
  List<Integer> groupsId=new ArrayList<Integer>();
  List<Group> groups=customer.getGroups();
  if (groups != null) {
    for (    Group group : groups) {
      groupsId.add(group.getId());
    }
    if (groupsId != null && groupsId.size() > 0) {
      List<Permission> permissions=permissionService.getPermissions(groupsId);
      for (      Permission permission : permissions) {
        GrantedAuthority auth=new SimpleGrantedAuthority(permission.getPermissionName());
        authorities.add(auth);
      }
    }
  }
  Authentication authenticationToken=new UsernamePasswordAuthenticationToken(userName,password,authorities);
  Authentication authentication=customerAuthenticationManager.authenticate(authenticationToken);
  SecurityContextHolder.getContext().setAuthentication(authentication);
}
