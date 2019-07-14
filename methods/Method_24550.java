public boolean getDSR(){
  try {
    return port.isDSR();
  }
 catch (  SerialPortException e) {
    throw new RuntimeException("Error reading the DSR line: " + e.getExceptionType());
  }
}
