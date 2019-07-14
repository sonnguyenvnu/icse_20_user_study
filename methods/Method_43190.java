public Set<String> getCurrencies(){
  return additionalProperties.keySet().stream().filter(key -> key.startsWith(PREFIX_TOTAL)).map(key -> StringUtils.remove(key,PREFIX_TOTAL)).collect(Collectors.toSet());
}
