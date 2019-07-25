/** 
 * Block until a packet arrives, which will be written into |packet|.
 * @param packet The destination for an incoming packet.
 * @return False if the socket is closed.
 */
private boolean receive(DatagramPacket packet){
  try {
    socket.receive(packet);
    return true;
  }
 catch (  IOException e) {
    Log.i(LOG_TAG,"Read failed",e);
    return false;
  }
}
