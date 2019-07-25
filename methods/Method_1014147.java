@Override public SerialPort open(String owner,int timeout) throws PortInUseException {
  try {
    id.getTelnetClient().setConnectTimeout(timeout);
    id.getTelnetClient().connect(uri.getHost(),uri.getPort());
    return new RxTxSerialPort(id);
  }
 catch (  Exception e) {
    throw new IllegalStateException(String.format("Unable to establish remote connection to serial port %s",uri),e);
  }
}
