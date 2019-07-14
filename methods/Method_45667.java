/** 
 * ???????????????????
 * @param container Container of client transport
 * @param channel   Exists channel from client
 * @return reverse client transport of exists channel
 */
public static ClientTransport getReverseClientTransport(String container,AbstractChannel channel){
  if (REVERSE_CLIENT_TRANSPORT_MAP == null) {
synchronized (ClientTransportFactory.class) {
      if (REVERSE_CLIENT_TRANSPORT_MAP == null) {
        REVERSE_CLIENT_TRANSPORT_MAP=new ConcurrentHashMap<String,ClientTransport>();
      }
    }
  }
  String key=NetUtils.channelToString(channel.remoteAddress(),channel.localAddress());
  ClientTransport transport=REVERSE_CLIENT_TRANSPORT_MAP.get(key);
  if (transport == null) {
synchronized (ClientTransportFactory.class) {
      transport=REVERSE_CLIENT_TRANSPORT_MAP.get(key);
      if (transport == null) {
        ClientTransportConfig config=new ClientTransportConfig().setProviderInfo(new ProviderInfo().setHost(channel.remoteAddress().getHostName()).setPort(channel.remoteAddress().getPort())).setContainer(container);
        transport=ExtensionLoaderFactory.getExtensionLoader(ClientTransport.class).getExtension(config.getContainer(),new Class[]{ClientTransportConfig.class},new Object[]{config});
        transport.setChannel(channel);
        REVERSE_CLIENT_TRANSPORT_MAP.put(key,transport);
      }
    }
  }
  return transport;
}
