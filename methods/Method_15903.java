@SuppressWarnings("unchecked") private Set<Object> parseSet(String json){
  if (!StringUtils.hasText(json)) {
    return null;
  }
  return (Set)JSON.parseObject(json,Set.class);
}
