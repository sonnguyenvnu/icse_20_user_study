/** 
 * Returns the host name of the  {@link Request}.
 * @deprecated Do not use this method. Get the remote or local address from {@link #context()} or getthe authority from  {@link #authority()}.
 * @return the host name. {@code null} if the {@link Request} has failed even before a connection isestablished.
 * @throws RequestLogAvailabilityException if this property is not available yet
 */
@Nullable @Deprecated default String host(){
  final RequestContext ctx=context();
  final InetSocketAddress addr;
  if (ctx instanceof ClientRequestContext) {
    addr=ctx.remoteAddress();
  }
 else {
    addr=ctx.localAddress();
  }
  return addr != null ? addr.getHostString() : null;
}
