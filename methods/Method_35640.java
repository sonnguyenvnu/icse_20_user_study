public static List<LoggedRequest> findAll(RequestPatternBuilder requestPatternBuilder){
  return defaultInstance.get().find(requestPatternBuilder);
}
