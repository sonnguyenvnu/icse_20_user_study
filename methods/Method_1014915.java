public boolean connect(String address,int baudRate){
  serialPort=new SerialPort(address);
  connected=false;
  try {
    int mask=SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
    serialPort.openPort();
    serialPort.setParams(baudRate,8,1,0);
    serialPort.setEventsMask(mask);
    connected=true;
  }
 catch (  SerialPortException ex) {
    System.err.println(ex);
  }
  return connected;
}
