/** 
 * Disables the given  {@link HttpMethod}s.
 * @param methods must not be {@literal null}.
 * @return
 */
public ConfigurableHttpMethods disable(HttpMethod... methods){
  Assert.notNull(methods,"HttpMethods must not be null!");
  List<HttpMethod> toRemove=Arrays.asList(methods);
  return new ConfigurableHttpMethods(this.methods.stream().filter(it -> !toRemove.contains(it)).collect(Collectors.toSet()));
}
