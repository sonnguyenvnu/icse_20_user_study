public Socket establish(InetAddress[] addresses) throws Exception {
  SocketFuture future=new SocketFuture();
  List<SocketRacer> racers=new ArrayList<SocketRacer>(addresses.length);
  int delay=0;
  Signal startSignal=null;
  for (  InetAddress address : addresses) {
    if (mMode == DualStackMode.IPV4_ONLY && !(address instanceof Inet4Address) || mMode == DualStackMode.IPV6_ONLY && !(address instanceof Inet6Address)) {
      continue;
    }
    delay+=mFallbackDelay;
    Signal doneSignal=new Signal(delay);
    SocketAddress socketAddress=new InetSocketAddress(address,mAddress.getPort());
    SocketRacer racer=new SocketRacer(future,mSocketFactory,socketAddress,mServerNames,mConnectTimeout,startSignal,doneSignal);
    racers.add(racer);
    startSignal=doneSignal;
  }
  return future.await(racers);
}
