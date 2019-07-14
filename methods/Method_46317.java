/** 
 * ?????IO???
 * @param config ?????
 * @return ???Boss???
 */
public static EventLoopGroup getServerIoEventLoopGroup(ServerTransportConfig config){
  String type=config.getProtocolType();
  EventLoopGroup ioGroup=serverIoGroups.get(type);
  if (ioGroup == null) {
synchronized (NettyHelper.class) {
      ioGroup=serverIoGroups.get(config.getProtocolType());
      if (ioGroup == null) {
        int ioThreads=config.getIoThreads();
        ioThreads=ioThreads <= 0 ? Math.max(8,SystemInfo.getCpuCores() + 1) : ioThreads;
        NamedThreadFactory threadName=new NamedThreadFactory("SEV-IO-" + config.getPort(),config.isDaemon());
        ioGroup=config.isUseEpoll() ? new EpollEventLoopGroup(ioThreads,threadName) : new NioEventLoopGroup(ioThreads,threadName);
        serverIoGroups.put(type,ioGroup);
        refCounter.putIfAbsent(ioGroup,new AtomicInteger(0));
      }
    }
  }
  refCounter.get(ioGroup).incrementAndGet();
  return ioGroup;
}
