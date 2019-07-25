public static <M extends JcrAbstractRoleMembership>M create(Node parentNode,Node roleNode,Class<M> memberClass,Object... args){
  Object[] argsWithRole=ArrayUtils.add(args,0,roleNode);
  return JcrUtil.getNodeList(parentNode,NODE_NAME).stream().map(node -> JcrUtil.getJcrObject(node,memberClass,args)).filter(memshp -> memshp.getRole().getSystemName().equals(JcrUtil.getName(roleNode))).findFirst().orElseGet(() -> {
    return JcrUtil.addJcrObject(parentNode,NODE_NAME,NODE_TYPE,memberClass,argsWithRole);
  }
);
}
