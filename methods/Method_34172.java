/** 
 * Adds a new custom authentication filter for the TokenEndpoint. Filters will be set upstream of the default BasicAuthenticationFilter.
 * @param filter
 */
public void addTokenEndpointAuthenticationFilter(Filter filter){
  this.tokenEndpointAuthenticationFilters.add(filter);
}
