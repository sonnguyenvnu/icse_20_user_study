public void idandname(HttpServletRequest req,TSUser user){
  List<TSRoleUser> roleUsers=systemService.findByProperty(TSRoleUser.class,"TSUser.id",user.getId());
  String roleId="";
  String roleName="";
  if (roleUsers.size() > 0) {
    for (    TSRoleUser tRoleUser : roleUsers) {
      roleId+=tRoleUser.getTSRole().getId() + ",";
      roleName+=tRoleUser.getTSRole().getRoleName() + ",";
    }
  }
  req.setAttribute("id",roleId);
  req.setAttribute("roleName",roleName);
}
