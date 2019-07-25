@Override public ByteBufAllocator alloc(){
  final Channel channel=channel();
  return channel != null ? channel.alloc() : PooledByteBufAllocator.DEFAULT;
}
