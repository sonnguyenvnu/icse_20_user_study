private static void map(String uri,String method,Class<? extends Action> actionClass){
  ACTION_MAPPINGS.computeIfAbsent(ResourceURIs.URI_PREFIX + uri,k -> new HashMap<>()).put(method,actionClass);
}
