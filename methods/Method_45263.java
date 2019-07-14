private ImmutableMap<String,Object> variables(final Request request){
  return ImmutableMap.<String,Object>builder().putAll(toVariableString(request)).put("now",new NowMethod()).put("random",new RandomMethod()).put("req",toTemplateRequest(request)).build();
}
