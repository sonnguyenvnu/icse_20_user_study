private boolean execute(@NotNull Match match,@NotNull ResolveState state){
  ResolveState matchState=state.put(DECLARING_SCOPE,true);
  return execute((Infix)match,matchState);
}
