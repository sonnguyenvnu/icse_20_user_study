public static <T extends TBase>ThriftCodec<T> create(final Class<T> thriftStructType,Function<TTransport,TProtocol> protocolFactory){
  return new ThriftCodec<T>(MoreSuppliers.of(thriftStructType),protocolFactory);
}
