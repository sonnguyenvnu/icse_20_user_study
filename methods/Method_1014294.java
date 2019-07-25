/** 
 * Removes all cached sockets.
 */
public void flush(){
synchronized (SocketHandler.class) {
    Integer[] portsToRemove=socketsPerPort.keySet().toArray(new Integer[0]);
    for (    Integer key : portsToRemove) {
      removeSocket(key);
    }
  }
}
