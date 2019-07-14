@Override public AuthenticationBuilder permission(String permissionJson){
  JSONArray jsonArray=JSON.parseArray(permissionJson);
  List<Permission> permissions=new ArrayList<>();
  for (int i=0; i < jsonArray.size(); i++) {
    JSONObject jsonObject=jsonArray.getJSONObject(i);
    SimplePermission permission=new SimplePermission();
    permission.setId(jsonObject.getString("id"));
    permission.setName(jsonObject.getString("name"));
    JSONArray actions=jsonObject.getJSONArray("actions");
    if (actions != null) {
      permission.setActions(new HashSet<>(actions.toJavaList(String.class)));
    }
    JSONArray dataAccess=jsonObject.getJSONArray("dataAccesses");
    if (null != dataAccess) {
      permission.setDataAccesses(dataAccess.stream().map(JSONObject.class::cast).map(dataJson -> dataBuilderFactory.create().fromJson(dataJson.toJSONString()).build()).filter(Objects::nonNull).collect(Collectors.toSet()));
    }
    permissions.add(permission);
  }
  authentication.setPermissions(permissions);
  return this;
}
