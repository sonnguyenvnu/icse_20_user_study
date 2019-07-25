@Override public void init(NutConfig config,ActionInfo ai) throws Throwable {
  if ("json".equals(ai.getOkView()) && String.class.equals(ai.getMethod().getReturnType())) {
    log.warn("Not a good idea : Return String ,and using JsonView!! (Using @Ok(\"raw\") or return map/list/pojo)--> " + Lang.simpleMethodDesc(ai.getMethod()));
  }
  view=evalView(config,ai,ai.getOkView());
  Class<?>[] params=ai.getMethod().getParameterTypes();
  for (int i=0; i < params.length; i++) {
    if (ViewModel.class.isAssignableFrom(params[i])) {
      index=i;
      break;
    }
  }
  if (view instanceof ViewZone)   ((ViewZone)view).setIndex(index);
}
