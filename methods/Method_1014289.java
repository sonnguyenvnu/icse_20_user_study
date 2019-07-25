/** 
 * Releases a unused port number.
 */
public synchronized void release(int port){
  for (  PortInfo portInfo : availablePorts) {
    if (portInfo.port == port) {
      portInfo.free=true;
    }
  }
}
