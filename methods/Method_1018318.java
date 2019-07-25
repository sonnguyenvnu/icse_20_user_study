/** 
 * Enables the given  {@link HttpMethod}s.
 * @param methods must not be {@literal null}.
 * @return
 */
public ConfigurableHttpMethods enable(HttpMethod... methods){
  Assert.notNull(methods,"HttpMethods must not be null!");
  List<HttpMethod> toAdd=Arrays.asList(methods);
  if (this.methods.containsAll(toAdd)) {
    return this;
  }
  return ConfigurableHttpMethods.of(Stream.concat(this.methods.stream(),toAdd.stream()).collect(Collectors.toSet()));
}
