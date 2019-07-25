public DatagramPacket write(OutgoingDatagramMessage message) throws UnsupportedDataException {
  StringBuilder statusLine=new StringBuilder();
  UpnpOperation operation=message.getOperation();
  if (operation instanceof UpnpRequest) {
    UpnpRequest requestOperation=(UpnpRequest)operation;
    statusLine.append(requestOperation.getHttpMethodName()).append(" * ");
    statusLine.append("HTTP/1.").append(operation.getHttpMinorVersion()).append("\r\n");
  }
 else   if (operation instanceof UpnpResponse) {
    UpnpResponse responseOperation=(UpnpResponse)operation;
    statusLine.append("HTTP/1.").append(operation.getHttpMinorVersion()).append(" ");
    statusLine.append(responseOperation.getStatusCode()).append(" ").append(responseOperation.getStatusMessage());
    statusLine.append("\r\n");
  }
 else {
    throw new UnsupportedDataException("Message operation is not request or response, don't know how to process: " + message);
  }
  StringBuilder messageData=new StringBuilder();
  messageData.append(statusLine);
  messageData.append(message.getHeaders().toString()).append("\r\n");
  if (log.isLoggable(Level.FINER)) {
    log.finer("Writing message data for: " + message);
    log.finer("---------------------------------------------------------------------------------");
    log.finer(messageData.toString().substring(0,messageData.length() - 2));
    log.finer("---------------------------------------------------------------------------------");
  }
  try {
    byte[] data=messageData.toString().getBytes("US-ASCII");
    log.fine("Writing new datagram packet with " + data.length + " bytes for: " + message);
    return new DatagramPacket(data,data.length,message.getDestinationAddress(),message.getDestinationPort());
  }
 catch (  UnsupportedEncodingException ex) {
    throw new UnsupportedDataException("Can't convert message content to US-ASCII: " + ex.getMessage(),ex,messageData);
  }
}
