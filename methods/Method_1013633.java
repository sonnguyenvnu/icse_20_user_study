public static boolean bind(Channel channel,NettyClient nettyClient){
  Attribute<NettyClient> attribute=channel.attr(KEY_CLIENT);
  if (attribute != null) {
    return false;
  }
synchronized (channel) {
    attribute=channel.attr(KEY_CLIENT);
    if (attribute == null) {
      return false;
    }
    attribute.set(nettyClient);
  }
  return true;
}
