@Override public List<LoggedRequest> findAll(RequestPatternBuilder requestPatternBuilder){
  return client.find(requestPatternBuilder);
}
