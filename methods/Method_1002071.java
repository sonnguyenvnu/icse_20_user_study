@Override public boolean check(Object[] permissions){
  ShiroUser user=ShiroKit.getUser();
  if (null == user) {
    return false;
  }
  ArrayList<Object> objects=CollectionUtil.newArrayList(permissions);
  String join=CollectionUtil.join(objects,",");
  if (ShiroKit.hasAnyRoles(join)) {
    return true;
  }
  return false;
}
