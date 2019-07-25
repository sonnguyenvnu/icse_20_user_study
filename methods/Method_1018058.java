@Override public P add(){
  JcrAccessControlUtil.addPermissions(this.actionNode,getManagementPrincipal(),Privilege.JCR_ALL);
  JcrAccessControlUtil.addPermissions(this.actionNode,SimplePrincipal.EVERYONE,Privilege.JCR_READ);
  return this.parentBuilder;
}
