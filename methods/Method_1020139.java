@PostMapping("login") public String login(Model model,String name,String pwd){
  User user=userService.getUserByNameAndPwd(name,pwd);
  if (null == user) {
    return "/security/login";
  }
  LoginUserCache.put(user);
  String roleName="admin";
  if (Objects.equals(roleName,user.getName())) {
    model.addAttribute("accordions",getAccordions(true,null));
  }
 else {
    List<UserRole> userRoles=userService.getUserRolesByUserId(user.getId());
    if (null == userRoles || 0 == userRoles.size()) {
      return "/security/login";
    }
    List<Long> roleIds=new ArrayList<Long>();
    for (    UserRole ur : userRoles) {
      roleIds.add(ur.getRoleId());
    }
    List<Role> roles=roleService.getRoles(roleIds);
    nativeCache.setRole(user.getId(),roles);
    LoginUserCache.put(user);
    List<Accordion> accordions=getAccordions(true,user.getId());
    model.addAttribute("accordions",accordions);
    LoginUserCache.setAccordions(user.getName(),accordions);
  }
  return "/layout/main";
}
