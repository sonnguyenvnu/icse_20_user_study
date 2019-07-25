/** 
 * Resolves this endpoint into a host endpoint associated with the specified {@link ClientRequestContext}.
 * @return the {@link Endpoint} resolved by {@link EndpointGroupRegistry}. {@code this} if this endpoint is already a host endpoint.
 */
public Endpoint resolve(ClientRequestContext ctx){
  if (isGroup()) {
    return EndpointGroupRegistry.selectNode(ctx,groupName);
  }
 else {
    return this;
  }
}
