/** 
 * read from owserver
 * @return the read packet
 * @throws OwException
 */
private OwserverPacket read(boolean noTimeoutException) throws OwException {
  OwserverPacket returnPacket=new OwserverPacket(OwserverPacketType.RETURN);
  try {
    if (owserverInputStream != null) {
      DataInputStream inputStream=owserverInputStream;
      returnPacket=new OwserverPacket(inputStream,OwserverPacketType.RETURN);
      logger.trace("read: {}",returnPacket);
    }
 else {
      logger.debug("input stream not available on read");
      closeOnError();
    }
  }
 catch (  EOFException e) {
  }
catch (  OwException e) {
    checkConnection();
    throw e;
  }
catch (  IOException e) {
    if (e.getMessage().equals("Read timed out") && noTimeoutException) {
      logger.trace("timeout - setting error code to -1");
      returnPacket.setPayload("timeout");
      returnPacket.setReturnCode(-1);
    }
 else {
      checkConnection();
      throw new OwException("I/O error: exception while reading packet - " + e.getMessage());
    }
  }
  return returnPacket;
}
