@Override protected ProducesRequestCondition customize(ProducesRequestCondition condition){
  if (!condition.isEmpty()) {
    return condition;
  }
  HashSet<String> mediaTypes=new LinkedHashSet<String>();
  mediaTypes.add(configuration.getDefaultMediaType().toString());
  mediaTypes.add(MediaType.APPLICATION_JSON_VALUE);
  return new ProducesRequestCondition(mediaTypes.toArray(new String[mediaTypes.size()]));
}
