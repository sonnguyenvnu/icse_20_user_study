@Override public AddressResolverGroup<InetSocketAddress> apply(EventLoopGroup eventLoopGroup){
  final DnsNameResolverBuilder nameResolverBuilder=new DnsNameResolverBuilder();
  nameResolverBuilder.nameServerProvider(DnsServerAddressStreamProviders.platformDefault());
  nameResolverBuilder.traceEnabled(true);
  customizers.forEach(customizer -> customizer.accept(nameResolverBuilder));
  nameResolverBuilder.channelType(TransportType.datagramChannelType(eventLoopGroup));
  nameResolverBuilder.socketChannelType(TransportType.socketChannelType(eventLoopGroup));
  return new DnsAddressResolverGroup(nameResolverBuilder);
}
