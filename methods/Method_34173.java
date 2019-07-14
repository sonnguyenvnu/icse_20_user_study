/** 
 * Sets a new list of custom authentication filters for the TokenEndpoint. Filters will be set upstream of the default BasicAuthenticationFilter.
 * @param filters The authentication filters to set.
 */
public void tokenEndpointAuthenticationFilters(List<Filter> filters){
  Assert.notNull(filters,"Custom authentication filter list must not be null");
  this.tokenEndpointAuthenticationFilters=new ArrayList<Filter>(filters);
}
