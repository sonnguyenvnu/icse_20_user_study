public static <M extends JcrAbstractRoleMembership>Optional<M> find(Node parentNode,String roleName,Class<M> memberClass,Object... args){
  return JcrUtil.getNodeList(parentNode,NODE_NAME).stream().map(node -> JcrUtil.getJcrObject(node,memberClass,args)).filter(memshp -> memshp.getRole().getSystemName().equals(roleName)).findFirst();
}
