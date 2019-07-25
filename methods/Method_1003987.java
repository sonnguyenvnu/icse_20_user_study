private EndpointStatus join(InetSocketAddress endpoint,Map<String,InetSocketAddress> additionalEndpoints,Optional<Integer> shardId) throws JoinException, InterruptedException {
  checkNotNull(endpoint);
  checkNotNull(additionalEndpoints);
  final MemberStatus memberStatus=new MemberStatus(endpoint,additionalEndpoints,shardId);
  Supplier<byte[]> serviceInstanceSupplier=new Supplier<byte[]>(){
    @Override public byte[] get(){
      return memberStatus.serializeServiceInstance();
    }
  }
;
  final Membership membership=group.join(serviceInstanceSupplier);
  return new EndpointStatus(){
    @Override public void update(    Status status) throws UpdateException {
      checkNotNull(status);
      LOG.warning("This method is deprecated. Please use leave() instead.");
      if (status == Status.DEAD) {
        leave();
      }
 else {
        LOG.warning("Status update has been ignored");
      }
    }
    @Override public void leave() throws UpdateException {
      memberStatus.leave(membership);
    }
  }
;
}
