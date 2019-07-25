public static synchronized ThriftClient build(Class type){
  if (pools.containsKey(type)) {
    return pools.get(type);
  }
  ThriftClient thriftClient=new ThriftClient();
  thriftClient.hold=type;
  pools.put(type,thriftClient);
  return thriftClient;
}
