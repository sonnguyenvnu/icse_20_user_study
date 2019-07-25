public void interfaceroleidandname(HttpServletRequest req,TSUser user){
  List<InterroleUserEntity> roleUsers=systemService.findByProperty(InterroleUserEntity.class,"TSUser.id",user.getId());
  String roleId="";
  String roleName="";
  if (roleUsers.size() > 0) {
    for (    InterroleUserEntity interroleUserEntity : roleUsers) {
      roleId+=interroleUserEntity.getInterroleEntity().getId() + ",";
      roleName+=interroleUserEntity.getInterroleEntity().getRoleName() + ",";
    }
  }
  req.setAttribute("roleId",roleId);
  req.setAttribute("roleName",roleName);
}
