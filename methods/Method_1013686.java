@Override default boolean worksfor(ActionContext t){
  return t instanceof PingActionContext;
}
