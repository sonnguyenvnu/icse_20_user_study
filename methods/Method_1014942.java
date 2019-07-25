@Override public void intercept(Invocation invocation){
  Permissions permissions=invocation.getMethod().getAnnotation(Permissions.class);
  if (permissions != null && permissions.value().length > 0) {
    JSONObject jsonObject=Aop.get(AdminRoleService.class).auth(BaseUtil.getUserId());
    List<String> arr=queryAuth(jsonObject,"");
    boolean isRelease=false;
    for (    String key : permissions.value()) {
      if (!isRelease) {
        if (arr.contains(key)) {
          isRelease=true;
        }
      }
    }
    if (!isRelease) {
      invocation.getController().renderJson(R.error("????"));
      return;
    }
  }
  invocation.invoke();
}
