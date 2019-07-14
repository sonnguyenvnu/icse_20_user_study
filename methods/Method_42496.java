@SuppressWarnings("rawtypes") @Override public List listByRoleIds(String roleIdsStr){
  List<String> roldIds=Arrays.asList(roleIdsStr.split(","));
  return super.getSessionTemplate().selectList(getStatement("listByRoleIds"),roldIds);
}
