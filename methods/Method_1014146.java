@Override public SerialPort open(String owner,int timeout) throws PortInUseException {
  try {
    final CommPort cp=id.open(owner,timeout);
    if (cp instanceof gnu.io.SerialPort) {
      return new RxTxSerialPort((gnu.io.SerialPort)cp);
    }
 else {
      throw new IllegalStateException(String.format("We expect an serial port instead of '%s'",cp.getClass()));
    }
  }
 catch (  gnu.io.PortInUseException e) {
    throw new PortInUseException();
  }
}
