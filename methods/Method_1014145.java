@Override public SerialPort open(String owner,int timeout) throws PortInUseException {
  try {
    final CommPort cp=id.open(owner,timeout);
    if (cp instanceof javax.comm.SerialPort) {
      return new SerialPortImpl((javax.comm.SerialPort)cp);
    }
 else {
      throw new IllegalStateException(String.format("We expect an serial port instead of '%s'",cp.getClass()));
    }
  }
 catch (  javax.comm.PortInUseException e) {
    throw new PortInUseException();
  }
}
