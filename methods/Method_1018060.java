@Override public P add(){
  JcrAccessControlUtil.addPermissions(this.actionsNode,getManagementPrincipal(),Privilege.JCR_ALL);
  JcrAccessControlUtil.addPermissions(this.actionsNode,SimplePrincipal.EVERYONE,Privilege.JCR_READ);
  return this.parentBuilder;
}
